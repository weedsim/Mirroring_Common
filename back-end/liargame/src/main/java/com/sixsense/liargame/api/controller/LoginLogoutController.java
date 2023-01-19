package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.auth.TokenInfo;
import com.sixsense.liargame.common.model.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginLogoutController {
    private final UserService userService;

    @PostMapping("/login")
    public TokenInfo login(@RequestBody UserDto userDto) {
        String id = userDto.getEmail();
        String password = userDto.getPassword();
        System.out.println(id);
        System.out.println(password);
        TokenInfo tokenInfo = userService.login(id, password);
        return tokenInfo;
    }
}
