package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "subject_id")
    private Long subjectId;

    @Builder
    public Word(Long id, String name, Long subjectId) {
        this.id = id;
        this.name = name;
        this.subjectId = subjectId;
    }
}
