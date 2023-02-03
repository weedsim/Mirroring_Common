package com.sixsense.liargame.api.response;

import lombok.Data;

@Data
public class RoomTokenResp {
    private Long roomId;

    public RoomTokenResp(Long roomId) {
        this.roomId = roomId;
    }
}
