package com.sixsense.liargame.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class UserInfo implements Serializable {
    private Long userId;
    private String name;
}
