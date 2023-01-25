package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.auth.TokenInfo;
import com.sixsense.liargame.common.model.response.UserDto;

public interface UserService {
    public TokenInfo login(String email, String password);
    public void signUp(UserDto userdto) ;
}
