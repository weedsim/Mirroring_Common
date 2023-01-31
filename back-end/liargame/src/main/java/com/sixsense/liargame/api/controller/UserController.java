package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.UserService;
import com.sixsense.liargame.common.model.response.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<?> signUp(@RequestBody UserDto userDto){
		userService.signUp(userDto);
//		System.out.println(userDto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/duplicate")
	public ResponseEntity<?> duplication(String email, String name){
		return new ResponseEntity<Boolean>(userService.isDuplication(email, name), HttpStatus.OK);
	}
}
