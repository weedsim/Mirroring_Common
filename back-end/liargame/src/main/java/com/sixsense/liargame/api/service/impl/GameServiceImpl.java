package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.RoomRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GameServiceImpl implements GameService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    Map<Integer, Emitters> emittersMap;

    public GameServiceImpl(UserRepository userRepository, RoomRepository roomRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
    }

    @Override
    public GameResultResp normalGameStart(Long userId) {
//        Long userId = userRepository.findByEmail(email).orElseThrow().getId();
//        Room room = roomRepository.findById(id).orElseThrow();
//        Emitters emitters = emittersMap.get(id);
//        room.setEmitters(emitters);
//
//        if (Objects.equals(room.getMaster(), userId)) {
//            GameResultResp result = room.start();
//            List<CustomEmitter> emittersList = emitters.getEmitterList();
//            Long liar = getUserId(emittersList, result.getLiar());
//            List<GameUserReq> userReqList = new ArrayList<>();
//            emittersList.forEach(emitter -> {
//                if (Objects.equals(emitter.getUserId(), liar)) {
//                    userReqList.add(new GameUserReq(emitter.getUserId(), LIAR));
//                    return;
//                }
//                userReqList.add(new GameUserReq(emitter.getUserId(), CITIZEN));
//            });
//            NormalGameHistoryReq normalGameHistoryReq =
//                    NormalGameHistoryReq.builder()
//                            .liar(liar)
//                            .winner(result.getWinner())
//                            .users(userReqList)
//                            .build();
//            insertNormalPlay(normalGameHistoryReq);
//        }
        return null;
    }

    @Override
    public GameResultResp spyGameStart(Long userId) {
        return null;
    }

    @Override
    public Vote vote(Vote vote) {
        return null;
    }

    @Override
    public void insertAnswer(String answer) {

    }
}
