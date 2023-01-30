package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.lib.Helper;
import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.Response;
import com.sixsense.liargame.common.model.request.UserRequestDto;
import com.sixsense.liargame.common.model.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
		if (errors.hasErrors()){
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.login(login);
	}

	@PostMapping("/reissue")
	public ResponseEntity<?> reissue(@Validated UserRequestDto.Reissue reissue, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.reissue(reissue);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@Validated UserRequestDto.Logout logout, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.logout(logout);
	}

	@GetMapping("/authority")
	public ResponseEntity<?> authority() {
		return userService.authority();
	}

	@GetMapping("/duplicate")
	public ResponseEntity<?> duplication(String email, String name){
		return new ResponseEntity<Boolean>(userService.isDuplication(email, name), HttpStatus.OK);
	}
}
