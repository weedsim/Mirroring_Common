package com.sixsense.liargame.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.api.request.AnswerReq;
import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.Game;
import com.sixsense.liargame.api.sse.GameManager;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.api.sse.SseResponse;
import com.sixsense.liargame.db.entity.*;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.SpyHistoryRepository;
import com.sixsense.liargame.db.repository.SpyPlayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final SpyHistoryRepository spyHistoryRepository;
    private final SpyPlayRepository spyPlayRepository;
    private final GameManager gameManager;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final Map<Integer, Room> rooms;
    private final Map<Integer, Game> games;
    private final ObjectMapper om;

    public GameServiceImpl(NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, SpyHistoryRepository spyHistoryRepository, SpyPlayRepository spyPlayRepository, GameManager gameManager, GlobalRoom globalRoom, ObjectMapper om) {
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.spyHistoryRepository = spyHistoryRepository;
        this.spyPlayRepository = spyPlayRepository;
        this.gameManager = gameManager;
        this.rooms = globalRoom.getRooms();
        this.games = globalRoom.getGames();
        this.om = om;
    }

    @Override
    public GameResultResp gameStart(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);

        if (Objects.equals(room.getMaster(), userId)) {
            room.start();
            Game game = gameManager.start(room);
            if (room.getMode().equals("normal")) {
                NormalHistory normalHistory =
                        NormalHistory.builder()
                                .liar(game.getLiarUserId())
                                .winner(game.getWinner())
                                .build();
                Long historyId = normalHistoryRepository.save(normalHistory).getId();
                List<NormalPlay> playList =
                        Arrays.stream(game.getParticipants())
                                .map(p -> new NormalPlay(p.getUserId(), historyId, getRole(p.getUserId(), game.getLiarUserId(), null)))
                                .collect(Collectors.toList());
                normalPlayRepository.saveAll(playList);

            } else {
                SpyHistory spyHistory = SpyHistory.builder()
                        .liar(game.getLiarUserId())
                        .spy(((SpyGame) game).getSpyUserId())
                        .winner(game.getWinner())
                        .build();
                Long historyId = spyHistoryRepository.save(spyHistory).getId();
                List<SpyPlay> playList =
                        Arrays.stream(game.getParticipants())
                                .map(p -> new SpyPlay(p.getUserId(), historyId, getRole(p.getUserId(), game.getLiarUserId(), ((SpyGame) game).getSpyUserId())))
                                .collect(Collectors.toList());
                spyPlayRepository.saveAll(playList);
            }
            room.end();
            GameResultResp gameResultResp = game.getResult();
            String result;
            try {
                result = om.writeValueAsString(gameResultResp);
            } catch (JsonProcessingException e) {
                return null;
            }
            room.getEmitters().sendToAll("message", new SseResponse("result", result));
            return gameResultResp;
        }
        return null;
    }

    private String getRole(Long userId, Long liar, Long spy) {
        if (Objects.equals(userId, liar)) {
            return LIAR;
        } else if (Objects.equals(userId, spy)) {
            return SPY;
        }
        return CITIZEN;
    }

    @Override
    public GameResultResp spyGameStart(Long userId) {
        return null;
    }

    @Override
    @Transactional
    public void vote(Vote vote, Integer gameId) {
        Game game = games.get(gameId);
        gameManager.vote(vote.getVoter(), vote.getTarget(), game.getVotes());
        System.out.println("투표 완료");
    }

    @Override
    @Transactional
    public void insertAnswer(AnswerReq answer) {
        Game game = games.get(answer.getRoomId());
        game.setAnswer(answer.getAnswer());
    }
}
