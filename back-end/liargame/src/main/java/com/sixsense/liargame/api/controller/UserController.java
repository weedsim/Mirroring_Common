package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto){
		userService.signUp(userDto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
