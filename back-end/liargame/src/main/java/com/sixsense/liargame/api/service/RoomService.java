package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoomService {
    void insert(RoomReq roomReq);

    void enter(Integer id);

    void exit(Integer id);

    void start(Integer id);

    void end(Integer id);

    void changeSetting(SettingDto settingDto);

    List<RoomResp> selectAll(Pageable pageable);

    default RoomResp toDto(Room room) {
        return RoomResp.builder()
                .id(room.getId())
                .title(room.getTitle())
                .mode(room.getMode())
                .curCount(room.getCurCount())
                .maxCount(room.getMaxCount())
                .isPlaying(room.getIsPlaying())
                .password(room.getPassword())
                .build();
    }

    default Room toEntity(RoomReq roomReq) {
        return Room.builder()
                .title(roomReq.getTitle())
                .mode(roomReq.getMode())
                .maxCount(roomReq.getMaxCount())
                .password(roomReq.getPassword())
                .build();
    }
}
