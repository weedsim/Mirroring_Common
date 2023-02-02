package com.sixsense.liargame.api.response;

public class RoomTokenResp {
    private Long roomId;
    private String token;

    public RoomTokenResp(Long roomId, String token) {
        this.roomId = roomId;
        this.token = token;
    }
}
