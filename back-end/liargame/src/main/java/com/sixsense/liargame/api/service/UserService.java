package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.auth.TokenInfo;

public interface UserService {
    public TokenInfo login(String email, String password);
}
