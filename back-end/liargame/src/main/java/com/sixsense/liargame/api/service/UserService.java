package com.sixsense.liargame.api.service;

import com.sixsense.liargame.security.auth.TokenInfo;
import com.sixsense.liargame.common.model.response.UserDto;

public interface UserService {
    public TokenInfo login(String email, String password);
    public void signUp(UserDto userdto) ;
    public boolean isDuplication(String email, String name);
}