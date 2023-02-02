package com.sixsense.liargame.api.sse;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalEmitter {
    private final Map<Long, Emitters> emittersMap = new ConcurrentHashMap<>();

    public Emitters getEmitters(Long roomId) {
        return emittersMap.get(roomId);
    }

    public void addEmitters(Long roomId, Emitters emitters) {
        emittersMap.put(roomId, emitters);
    }

    public void removeEmitters(Long roomId) {
        emittersMap.remove(roomId);
    }


}