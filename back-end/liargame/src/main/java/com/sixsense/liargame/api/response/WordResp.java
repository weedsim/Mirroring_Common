package com.sixsense.liargame.api.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WordResp {
    private Long id;
    private String name;

    @Builder
    public WordResp(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
