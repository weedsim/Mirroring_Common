package com.sixsense.liargame.db.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode
public class PlayPK implements Serializable {
    @Id
    private Long userId;
    @Id
    private Long historyId;
}
