package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.RoomReq;
import com.sixsense.liargame.api.response.RoomResp;
import com.sixsense.liargame.common.model.RoomDetail;
import com.sixsense.liargame.common.model.SettingDto;
import com.sixsense.liargame.db.entity.Room;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

public interface RoomService {
    RoomDetail insert(Long userId, RoomReq roomReq) throws OpenViduJavaClientException, OpenViduHttpException;

    RoomDetail enter(Long userId, Integer roomId);

    void ban(Long userId, Integer roomId, Long banId);

    void exit(Long userId, Integer roomId);

    void changeSetting(Long userId, SettingDto settingDto);

    List<RoomResp> selectAll(Pageable pageable);

    Integer last();

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
                .timeout(roomReq.getTimeout())
                .title(roomReq.getTitle())
                .mode(roomReq.getMode())
                .maxCount(roomReq.getMaxCount())
                .password(roomReq.getPassword())
                .build();
    }

    default RoomDetail toDetail(Room room) {
        return RoomDetail.builder()
                .roomId(room.getId())
                .title(room.getTitle())
                .maxCount(room.getMaxCount())
                .timeout(room.getTimeout())
                .isPlaying(room.getIsPlaying())
                .mode(room.getMode())
                .password(room.getPassword())
                .master(room.getMaster())
                .participants(room.getParticipants())
                .build();
    }

    void ready(Long userId, Integer roomId);
}
