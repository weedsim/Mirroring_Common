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

    public UserInfo(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
