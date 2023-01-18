package com.sixsense.liargame.db.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode
public class PlayPK implements Serializable {
    @Id
    private Long userId;
    @Id
    private Long historyId;
}
