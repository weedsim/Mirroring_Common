package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.lib.Helper;
import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.UserRequestDto;
import com.sixsense.liargame.security.auth.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final Response response;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Validated @RequestBody UserRequestDto.SignUp signUp,
                                    Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.signUp(signUp);
    }

    @GetMapping("/register-email")
    public ResponseEntity<?> registerEmail(@RequestParam("email") String email,
                                           @RequestParam("mail-key") String key) {
        return userService.registerEmail(email, key);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated @RequestBody UserRequestDto.Login login,
                                   Errors errors) {
        // validation check
//        if (errors.hasErrors()) {
//            return response.invalidFields(Helper.refineErrors(errors));
//        }
        return userService.login(login);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(JwtProperties.AUTHORIZATION) String accessToken,
                                    @RequestHeader(JwtProperties.REFRESH_TOKEN) String refreshToken) {
        UserRequestDto.Logout logout = UserRequestDto.Logout.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return userService.logout(logout);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestHeader(JwtProperties.AUTHORIZATION) String accessToken,
                                     @RequestHeader(JwtProperties.REFRESH_TOKEN) String refreshToken) {
        UserRequestDto.Reissue reissue = UserRequestDto.Reissue.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return userService.reissue(reissue);
    }

    @PutMapping("/modify/name")
    public ResponseEntity<?> updateUserName(@RequestHeader(JwtProperties.AUTHORIZATION) String accessToken,
                                            @RequestBody Map<String, String> header) {
        UserRequestDto.ModifyName modify = UserRequestDto.ModifyName.builder()
                .accessToken(accessToken)
                .name(header.get("name"))
                .build();

        return userService.updateName(modify);
    }

    @PutMapping("/modify/password")
    public ResponseEntity<?> updateUserPassword(@RequestHeader(JwtProperties.AUTHORIZATION) String accessToken,
                                                @RequestBody Map<String, String> header) {
        UserRequestDto.ModifyPassword modify = UserRequestDto.ModifyPassword.builder()
                .accessToken(accessToken)
                .password(header.get("password"))
                .build();

        return userService.updatePassword(modify);
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader(JwtProperties.AUTHORIZATION) String accessToken) {
        UserRequestDto.UserInfo userInfo = UserRequestDto.UserInfo
                .builder()
                .accessToken(accessToken)
                .build();

        return userService.getUserInfo(userInfo);
    }

    @GetMapping("/authority")
    public ResponseEntity<?> authority() {
        return userService.authority();
    }

    @GetMapping("/duplicate")
    public ResponseEntity<?> duplication(@RequestParam(required = false) String email,
                                         @RequestParam(required = false) String name) {
        System.out.println("test");
        return new ResponseEntity<Boolean>(userService.isDuplication(email, name), HttpStatus.OK);
    }
}