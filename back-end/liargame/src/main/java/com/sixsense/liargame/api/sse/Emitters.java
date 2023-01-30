package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.common.model.CustomEmitter;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Emitters {
    @Getter
    private final List<CustomEmitter> emitters = new CopyOnWriteArrayList<>();

    public void add(CustomEmitter emitter) {
        this.emitters.add(emitter);
        emitter.onCompletion(() -> {
            this.emitters.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(emitter::complete);
    }

    public void remove(Long userId) {
        emitters.removeIf(emitter -> Objects.equals(emitter.getUserId(), userId));
    }

    public int size() {
        return emitters.size();
    }

    public void sendMessage(String name, String message) {
        emitters.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name(name)
                        .data(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CustomEmitter findLiar(Long userId) {
        for (CustomEmitter emitter : emitters) {
            if (Objects.equals(emitter.getUserId(), userId))
                return emitter;
        }
        return null;
    }

    public List<Long> getParticipants() {
        return emitters.stream().map(CustomEmitter::getUserId).collect(Collectors.toList());
    }
}