package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Spy")
@Getter
@NoArgsConstructor
public class SpyHistory extends History {
    private Long spy;
    @Builder
    public SpyHistory(Long id, Long liar, String winner, Long spy) {
        super(id, liar, winner);
        this.spy = spy;
    }
}
