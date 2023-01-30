package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.request.NormalGameHistoryReq;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.common.model.CustomEmitter;
import com.sixsense.liargame.common.model.request.GameUserReq;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.NormalHistory;
import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.RoomRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    Map<Integer, Emitters> emittersMap;

    public RoomServiceImpl(UserRepository userRepository, RoomRepository roomRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        emittersMap = new ConcurrentHashMap<>();
    }

    @Override
    public Integer insert(RoomReq roomReq) {
        Room room = roomRepository.save(toEntity(roomReq));
        Integer id = room.getId();

        emittersMap.put(id, room.getEmitters());
        return id;
    }

    @Override
    @Transactional
    public Integer enter(String email, Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        room.setEmitters(emittersMap.get(id));
        if (room.getMaxCount() <= room.getCurCount())
            return null;
        User user = userRepository.findByEmail(email).orElseThrow();
        room.enter(user.getId(), user.getName());
        return id;
    }

    @Override
    @Transactional
    public void exit(String email, Integer id) {
        Long userId = userRepository.findByEmail(email).orElseThrow().getId();
        Room room = roomRepository.findById(id).orElseThrow();
        room.setEmitters(emittersMap.get(id));
        room.exit(userId);
        if (room.getCurCount() <= 0) {
            roomRepository.delete(room);
            emittersMap.remove(id);
        }
    }

    @Override
    @Transactional
    public void normalStart(String email, Integer id) {
        Long userId = userRepository.findByEmail(email).orElseThrow().getId();
        Room room = roomRepository.findById(id).orElseThrow();
        Emitters emitters = emittersMap.get(id);
        room.setEmitters(emitters);

        if (Objects.equals(room.getMaster(), userId)) {
            GameResultResp result = room.start();
            List<CustomEmitter> emittersList = emitters.getEmitters();
            Long liar = getUserId(emittersList, result.getLiar());
            List<GameUserReq> userReqList = new ArrayList<>();
            emittersList.forEach(emitter -> {
                if (Objects.equals(emitter.getUserId(), liar)) {
                    userReqList.add(new GameUserReq(emitter.getUserId(), LIAR));
                    return;
                }
                userReqList.add(new GameUserReq(emitter.getUserId(), CITIZEN));
            });
            NormalGameHistoryReq normalGameHistoryReq =
                    NormalGameHistoryReq.builder()
                            .liar(liar)
                            .winner(result.getWinner())
                            .users(userReqList)
                            .build();
            insertNormalPlay(normalGameHistoryReq);
        }
    }

    private Long getUserId(List<CustomEmitter> emitters, String name) {
        for (CustomEmitter emitter : emitters) {
            if (emitter.getName().equals(name))
                return emitter.getUserId();
        }
        return null;
    }

    @Override
    @Transactional
    public void changeSetting(String email, SettingDto settingDto) {
        Long userId = userRepository.findByEmail(email).orElseThrow().getId();
        Room room = roomRepository.findById(settingDto.getId()).orElseThrow();
        if (Objects.equals(userId, room.getMaster()))
            room.changeSetting(settingDto);
    }

    @Override
    public List<RoomResp> selectAll(Pageable pageable) {
        return roomRepository.findAll(pageable).stream().map(this::toDto).collect(Collectors.toList());
    }

    public void insertNormalPlay(NormalGameHistoryReq history) {
        NormalHistory normalHistory = NormalHistory.builder()
                .liar(history.getLiar())
                .winner(history.getWinner())
                .build();
        Long historyId = normalHistoryRepository.save(normalHistory).getId();
        List<NormalPlay> list = history.getUsers().stream().map(user ->
                        NormalPlay.builder()
                                .historyId(historyId)
                                .userId(user.getUserId())
                                .role(user.getRole())
                                .build())
                .collect(Collectors.toList());
        normalPlayRepository.saveAll(list);
    }

}
