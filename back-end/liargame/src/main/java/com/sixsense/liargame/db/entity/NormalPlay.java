package com.sixsense.liargame.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@Getter
@IdClass(Play.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalPlay extends Play implements Serializable {
    private String role;
}
