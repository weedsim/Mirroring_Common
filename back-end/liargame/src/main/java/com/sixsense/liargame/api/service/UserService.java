package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.UserRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp);

    public ResponseEntity<?> login(UserRequestDto.Login login);

    public ResponseEntity<?> reissue(UserRequestDto.Reissue reissue);

    public ResponseEntity<?> logout(UserRequestDto.Logout logout);

    public ResponseEntity<?> authority();

    public ResponseEntity<?> updateName(UserRequestDto.ModifyName name);

    public ResponseEntity<?> updatePassword(UserRequestDto.ModifyPassword password);

    public ResponseEntity<?> getUserInfo(UserRequestDto.UserInfo userInfo);

    public boolean isDuplication(String email, String name);

    public ResponseEntity<?> registerEmail(String email, String key);
}
