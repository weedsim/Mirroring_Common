package com.sixsense.liargame.api.response;

import lombok.Data;

@Data
public class RoomTokenResp {
    private Integer roomId;

    public RoomTokenResp(Integer roomId) {
        this.roomId = roomId;
    }
}
