package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor
@DiscriminatorValue("Normal")
public class NormalPlay extends Play {
    @Builder
    public NormalPlay(Long userId, Long historyId, String role) {
        super(userId, historyId, role);
    }
}
