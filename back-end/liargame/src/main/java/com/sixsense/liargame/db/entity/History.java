package com.sixsense.liargame.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class History extends PlayBaseTime {
    private Long liar;
    private String winner;
}
