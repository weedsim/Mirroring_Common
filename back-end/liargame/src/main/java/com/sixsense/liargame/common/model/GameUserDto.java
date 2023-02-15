package com.sixsense.liargame.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameUserDto {
    private Long userId;
    private String role;
}
