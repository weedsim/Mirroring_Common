package com.sixsense.liargame.common.model;

import lombok.*;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Vote implements Serializable {
    private Integer voter;
    @Setter
    private Integer target;
}
