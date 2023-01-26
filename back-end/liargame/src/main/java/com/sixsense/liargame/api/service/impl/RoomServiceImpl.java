package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.repository.RoomRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void insert(RoomReq roomReq) {
        roomRepository.save(toEntity(roomReq));
    }

    @Override
    @Transactional
    public void enter(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        if (room.getMaxCount() > room.getCurCount())
            room.enter();
    }

    @Override
    @Transactional
    public void exit(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        room.exit();
        if (room.getCurCount() <= 0)
            roomRepository.delete(room);
    }

    @Override
    @Transactional
    public void start(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        room.start();
    }

    @Override
    @Transactional
    public void end(Integer id) {
        Room room = roomRepository.findById(id).orElseThrow();
        room.end();
    }

    @Override
    @Transactional
    public void changeSetting(SettingDto settingDto) {
        Room room = roomRepository.findById(settingDto.getId()).orElseThrow();
        room.changeSetting(settingDto);
    }

    @Override
    public List<RoomResp> selectAll(Pageable pageable) {
        return roomRepository.findAll(pageable).stream().map(this::toDto).collect(Collectors.toList());
    }
}
