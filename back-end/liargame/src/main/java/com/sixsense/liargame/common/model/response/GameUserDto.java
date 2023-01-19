package com.sixsense.liargame.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class GameUserDto {
    private Long id;
    private String name;
    private String role;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameUserDto) {
            GameUserDto userObj = (GameUserDto) obj;
            return userObj.getId().equals(id) && userObj.getRole().equals(role) && userObj.getName().equals(name);
        }
        return false;
    }
}