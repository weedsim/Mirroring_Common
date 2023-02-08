package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.sse.CustomEmitter;

public interface SseService {
    CustomEmitter connect(Integer roomId, Long userId, String name);

}
