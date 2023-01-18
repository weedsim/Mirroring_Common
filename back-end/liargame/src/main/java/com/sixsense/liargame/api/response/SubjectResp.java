package com.sixsense.liargame.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectResp {
    private Long id;
    private String name;

    @Builder
    public SubjectResp(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
