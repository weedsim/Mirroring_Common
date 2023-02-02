package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.api.sse.GameManager;
import com.sixsense.liargame.api.sse.GlobalEmitter;
import com.sixsense.liargame.api.sse.NormalGame;
import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.db.entity.NormalHistory;
import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final GameManager gameManager;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final GlobalEmitter globalEmitter;
    private final NormalGameRepository normalGameRepository;

    public GameServiceImpl(UserRepository userRepository, RoomRepository roomRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GameManager gameManager, GlobalEmitter globalEmitter, NormalGameRepository normalGameRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.gameManager = gameManager;
        this.globalEmitter = globalEmitter;
        this.normalGameRepository = normalGameRepository;
    }

    @Override
    public GameResultResp normalGameStart(Long userId, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        Emitters emitters = globalEmitter.getEmitters(roomId);
        room.setEmitters(emitters);

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
    public void vote(Vote vote, Long GameId) {
        NormalGame game = normalGameRepository.findById(GameId).orElseThrow();
        gameManager.vote(vote.getVoter(), vote.getTarget(), game.getVotes());
    }

    @Override
    @Transactional
    public void insertAnswer(String answer, Long GameId) {
        NormalGame game = normalGameRepository.findById(GameId).orElseThrow();
        game.setAnswer(answer);
    }
}
