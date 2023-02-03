package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.GameManager;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.api.sse.NormalGame;
import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.db.entity.NormalHistory;
import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final GameManager gameManager;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final Map<Integer, Room> rooms;
    private final Map<Integer, NormalGame> games;

    public GameServiceImpl(UserRepository userRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GameManager gameManager, GlobalRoom globalRoom) {
        this.userRepository = userRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.gameManager = gameManager;
        this.rooms = globalRoom.getRooms();
        this.games = globalRoom.getGames();
    }

    @Override
    public GameResultResp normalGameStart(Long userId, Long roomId) {
        Room room = rooms.get(roomId);

        if (Objects.equals(room.getMaster(), userId)) {
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
            return normalGame.getResult();
        }
        return null;
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
    }

    @Override
    @Transactional
    public void insertAnswer(String answer, Integer gameId) {
        NormalGame game = games.get(gameId);
        game.setAnswer(answer);
    }
}
