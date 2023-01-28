package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.request.UserRequestDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    public ResponseEntity<?> signUp(UserRequestDto.SignUp signUp);
    public ResponseEntity<?> login(UserRequestDto.Login login);
    public boolean isDuplication(String email, String name);
}
