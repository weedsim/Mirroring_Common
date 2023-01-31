package com.sixsense.liargame.api.sse;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalEmitter {
    private final Map<Integer, Emitters> emitters = new ConcurrentHashMap<>();
}
