package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.request.UserRequestDto;
import com.sixsense.liargame.security.auth.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final Response response;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto.SignUp signUp) {
        // validation check
        return userService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto.Login login) {
        // validation check
        return userService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestHeader(name = JwtProperties.REFRESH_TOKEN) String refreshToken, @RequestHeader(name = JwtProperties.ACCESS_TOKEN) String accessToken) {
        // validation check
        UserRequestDto.Reissue reissue = new UserRequestDto.Reissue(accessToken, refreshToken);
        return userService.reissue(reissue);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(name = JwtProperties.REFRESH_TOKEN) String refreshToken, @RequestHeader(name = JwtProperties.ACCESS_TOKEN) String accessToken) {
        // validation check
        UserRequestDto.Logout logout = new UserRequestDto.Logout(accessToken, refreshToken);
        return userService.logout(logout);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> updateUserInfo(@RequestHeader(name = JwtProperties.REFRESH_TOKEN) String accessToken, @RequestBody UserRequestDto.Modify modify) {
        // validation check
        modify.setAccessToken(accessToken);
        return userService.modify(modify);
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader(name = JwtProperties.ACCESS_TOKEN) String accessToken) {
        // validation check
        UserRequestDto.UserInfo userInfo = new UserRequestDto.UserInfo(accessToken);
        return userService.getUserInfo(userInfo);
    }

    @GetMapping("/authority")
    public ResponseEntity<?> authority() {
        return userService.authority();
    }

    @GetMapping("/duplicate")
    public ResponseEntity<?> duplication(String email, String name) {
        return new ResponseEntity<Boolean>(userService.isDuplication(email, name), HttpStatus.OK);
    }
}