package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("Spy")
public class SpyPlay extends Play {
    @Builder
    public SpyPlay(Long userId, Long historyId, String role) {
        super(userId, historyId, role);
    }
}
