package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public interface RoomService {
    Integer insert(RoomReq roomReq);

    Integer enter(String email, Integer id);

    void exit(String email, Integer id);

    void normalStart(String email, Integer id);

    void changeSetting(String email, SettingDto settingDto);

    List<RoomResp> selectAll(Pageable pageable);

    default RoomResp toDto(Room room) {
        return RoomResp.builder()
                .id(room.getId())
                .title(room.getTitle())
                .mode(room.getMode())
                .curCount(room.getCurCount())
                .maxCount(room.getMaxCount())
                .isPlaying(room.getIsPlaying())
                .isPrivate(StringUtils.hasText(room.getPassword()))
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
