package com.sixsense.liargame.common.model;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class CustomEmitter extends SseEmitter {
    private final Long userId;
    private final String name;

    public CustomEmitter(Long userId, Long timeout, String name) {
        super(timeout);
        this.userId = userId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getUserId() {
        return userId;
    }

}
