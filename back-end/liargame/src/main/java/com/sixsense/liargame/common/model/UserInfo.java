package com.sixsense.liargame.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserInfo implements Serializable {
    private Long userId;
    private String name;
    @Setter
    private Boolean isReady;
    @Setter
    private Boolean isJoin;
    @Setter
    private Boolean mic;
    @Setter
    private Boolean cam;

    public UserInfo(Long userId, String name) {
        this.userId = userId;
        this.name = name;
        this.isReady = false;
        this.isJoin = true;
        this.mic = false;
        this.cam = false;
    }
}
