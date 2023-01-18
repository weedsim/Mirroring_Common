package com.sixsense.liargame.common.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectDto {
    private Long id;
    private String name;

    @Builder
    public SubjectDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
