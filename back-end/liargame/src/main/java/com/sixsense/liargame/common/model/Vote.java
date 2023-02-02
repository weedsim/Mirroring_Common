package com.sixsense.liargame.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Vote implements Serializable {
    private Integer voter;
    @Setter
    private Integer target;
}
