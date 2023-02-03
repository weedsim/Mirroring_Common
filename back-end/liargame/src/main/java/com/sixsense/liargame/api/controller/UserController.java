package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.lib.Helper;
import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.request.UserRequestDto;
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
    public ResponseEntity<?> signUp(@Validated UserRequestDto.SignUp signUp, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.signUp(signUp);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Validated UserRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.login(login);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("access-token") String accessToken, @RequestHeader("refresh-token") String refreshToken) {
        UserRequestDto.Logout logout = UserRequestDto.Logout.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return userService.logout(logout);
    }


    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestHeader UserRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return userService.reissue(reissue);
    }

    @PutMapping("/modify")
    public ResponseEntity<?> updateUserInfo(@RequestHeader("access-token") String accessToken,
                                            String name,
                                            String password) {
        System.out.println(name);
        System.out.println(password);
        UserRequestDto.Modify modify = UserRequestDto.Modify.builder()
                .accessToken(accessToken)
                .name(name)
                .password(password)
                .build();

        return userService.modify(modify);
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo(@RequestHeader("access-token") String accessToken) {
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
    public ResponseEntity<?> duplication(String email, String name) {
        return new ResponseEntity<Boolean>(userService.isDuplication(email, name), HttpStatus.OK);
    }
}