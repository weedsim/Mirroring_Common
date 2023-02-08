package com.sixsense.liargame.api.sse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SseResponse {
    private String type;
    private String value;
}
