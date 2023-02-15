package com.sixsense.liargame.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserInfoDto {
    private String name;
    private String email;
}
