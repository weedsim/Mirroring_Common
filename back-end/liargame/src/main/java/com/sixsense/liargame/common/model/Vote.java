package com.sixsense.liargame.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Vote {
    private Long voter;
    @Setter
    private Long target;
}
