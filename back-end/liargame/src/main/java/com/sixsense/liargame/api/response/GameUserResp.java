package com.sixsense.liargame.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GameUserResp {
    private Long id;
    private String name;
    private String role;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameUserResp) {
            GameUserResp userObj = (GameUserResp) obj;
            return userObj.getId().equals(id) && userObj.getRole().equals(role) && userObj.getName().equals(name);
        }
        return false;
    }
}