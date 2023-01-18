package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@DiscriminatorValue("normal")
@Getter
@NoArgsConstructor
public class NormalHistory extends History {

    @Builder
    public NormalHistory(Long id, Long liar, String winner) {
        super(id, liar, winner);
    }
}
