package com.sixsense.liargame.db.entity;

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
