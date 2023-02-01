package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.GlobalEmitter;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.RoomRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
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
    private final GlobalEmitter globalEmitter;

    public RoomServiceImpl(UserRepository userRepository, RoomRepository roomRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GlobalEmitter globalEmitter) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.globalEmitter = globalEmitter;
    }

    @Override
    public Long insert(RoomReq roomReq) {
        Room room = roomRepository.save(toEntity(roomReq));
        Long roomId = room.getId();
        globalEmitter.addEmitters(roomId, room.getEmitters());
        return roomId;
    }

    @Override
    @Transactional
    public void enter(Long userId, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setEmitters(globalEmitter.getEmitters(roomId));
        if (room.getMaxCount() > room.getCurCount()) {
            User user = userRepository.findById(userId).orElseThrow();
            room.enter(user.getId(), user.getName());
        }
    }

    @Override
    @Transactional
    public void exit(String email, Long roomId) {
        Long userId = userRepository.findByEmail(email).orElseThrow().getId();
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setEmitters(globalEmitter.getEmitters(roomId));
        room.exit(userId);
        if (room.getCurCount() <= 0) {
            roomRepository.delete(room);
            globalEmitter.removeEmitters(roomId);
        }
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

}
