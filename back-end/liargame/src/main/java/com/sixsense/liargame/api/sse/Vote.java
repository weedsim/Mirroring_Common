package com.sixsense.liargame.api.sse;

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
