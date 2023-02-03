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
@Validated
public class UserController {

	private final UserService userService;

	private final Response response;

	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserRequestDto.SignUp signUp, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.signUp(signUp);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserRequestDto.Login login, Errors errors) {
		// validation check
		if (errors.hasErrors()){
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.login(login);
	}

	@PostMapping("/reissue")
	public ResponseEntity<?> reissue(@RequestHeader UserRequestDto.Reissue reissue, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.reissue(reissue);
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader UserRequestDto.Logout logout, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.logout(logout);
	}

	@PutMapping("/modify")
	public ResponseEntity<?> updateUserInfo(@RequestHeader String accessToken, @RequestBody UserRequestDto.Modify modify, Errors errors) {
		// validation check
		if (errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		modify.setAccessToken(accessToken);
		return userService.modify(modify);
	}

	@GetMapping
	public ResponseEntity<?> getUserInfo(@RequestBody UserRequestDto.UserInfo userInfo, Errors errors) {
		// validation check
		System.out.println(userInfo.toString());
		if(errors.hasErrors()) {
			return response.invalidFields(Helper.refineErrors(errors));
		}
		return userService.getUserInfo(userInfo);
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
