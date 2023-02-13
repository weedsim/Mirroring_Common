package com.sixsense.liargame.api.sse;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vote {
    private Integer voter;
    @Setter
    private Integer target;
}
