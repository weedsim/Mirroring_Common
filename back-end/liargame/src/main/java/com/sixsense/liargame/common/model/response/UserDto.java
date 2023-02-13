package com.sixsense.liargame.common.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String role;
}
