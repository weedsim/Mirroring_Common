package com.sixsense.liargame.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String role;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserDto) {
            UserDto userObj = (UserDto) obj;
            return userObj.getId().equals(id) && userObj.getRole().equals(role) && userObj.getName().equals(name);
        }
        return false;
    }
}
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, SPY, CITIZEN, start++);
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, SPY, CITIZEN, start++);
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, LIAR, LIAR, start++);
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, LIAR, CITIZEN, start++);
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, SPY, LIAR, start++);
//        spyGame(userId, LIAR, LIAR, start++);
//        spyGame(userId, CITIZEN, CITIZEN, start++);
//        spyGame(userId, LIAR, CITIZEN, start++);//71