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
public class SpyPlay extends PlayPK implements Serializable {
    private String role;

    @Builder
    public SpyPlay(Long userId, Long historyId, String role) {
        super(userId, historyId);
        this.role = role;
    }
}
