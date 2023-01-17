package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@IdClass(PlayPK.class)
@NoArgsConstructor
public class NormalPlay extends PlayPK implements Serializable {
    private String role;

    @Builder
    public NormalPlay(Long userId, Long historyId, String role) {
        super(userId, historyId);
        this.role = role;
    }
}
