package com.sixsense.liargame.api.sse;

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
