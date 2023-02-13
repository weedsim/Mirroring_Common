package com.sixsense.liargame.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.api.request.AnswerReq;
import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.*;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.db.entity.NormalHistory;
import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
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
    private final GameManager gameManager;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final Map<Integer, Room> rooms;
    private final Map<Integer, NormalGame> games;
    private final ObjectMapper om;

    public GameServiceImpl(NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GameManager gameManager, GlobalRoom globalRoom, ObjectMapper om) {
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.gameManager = gameManager;
        this.rooms = globalRoom.getRooms();
        this.games = globalRoom.getGames();
        this.om = om;
    }

    @Override
    public GameResultResp normalGameStart(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);

        if (Objects.equals(room.getMaster(), userId) && isAllReady(room)) {
            room.start();
            NormalGame normalGame = gameManager.start(room);

            NormalHistory normalHistory =
                    NormalHistory.builder()
                            .liar(normalGame.getLiarUserId())
                            .winner(normalGame.getWinner())
                            .build();
            Long historyId = normalHistoryRepository.save(normalHistory).getId();
            List<NormalPlay> playList =
                    Arrays.stream(normalGame.getParticipants())
                            .map(p -> new NormalPlay(p.getUserId(), historyId, getRole(p.getUserId(), normalGame.getLiarUserId())))
                            .collect(Collectors.toList());
            normalPlayRepository.saveAll(playList);
            room.end();
            GameResultResp gameResultResp = normalGame.getResult();
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

    private boolean isAllReady(Room room) {
        List<UserInfo> participants = room.getParticipants();
        for (UserInfo user : participants) {
            if (!user.getIsReady() && !Objects.equals(room.getMaster(), user.getUserId()))
                return false;
        }
        return true;
    }

    private String getRole(Long userId, Long liar) {
        if (Objects.equals(userId, liar)) {
            return LIAR;
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
        NormalGame game = games.get(gameId);
        gameManager.vote(vote.getVoter(), vote.getTarget(), game.getVotes());
        System.out.println("투표 완료");
    }

    @Override
    @Transactional
    public void insertAnswer(AnswerReq answer) {
        NormalGame game = games.get(answer.getRoomId());
        game.setAnswer(answer.getAnswer());
    }
}
