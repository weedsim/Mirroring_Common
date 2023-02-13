package com.sixsense.liargame.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "mode")
@IdClass(PlayPK.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public abstract class Play{
    @Id
    private Long userId;
    @Id
    private Long historyId;
    private String role;
}
