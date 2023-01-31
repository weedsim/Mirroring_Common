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
    private final List<CustomEmitter> emitterList = new CopyOnWriteArrayList<>();
    private CustomEmitter liar;
    private CustomEmitter spy;

    public void add(CustomEmitter emitter) {
        this.emitterList.add(emitter);
        emitter.onCompletion(() -> {
            this.emitterList.remove(emitter);    // 만료되면 리스트에서 삭제
        });
        emitter.onTimeout(emitter::complete);
    }

    public void remove(Long userId) {
        emitterList.removeIf(emitter -> Objects.equals(emitter.getUserId(), userId));
    }

    public int size() {
        return emitterList.size();
    }

    public void sendToAll(String name, String message) {
        sendToCitizens(name, message);
        sendToLiar(name, message);
        if (spy != null)
            sendToSpy(name, message);
    }

    public void sendToLiar(String name, String message) {
        try {
            liar.send(SseEmitter.event()
                    .name(name)
                    .data(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToSpy(String name, String message) {
        try {
            spy.send(SseEmitter.event()
                    .name(name)
                    .data(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToCitizens(String name, String message) {
        emitterList.forEach(emitter -> {
            try {
                emitter.send(SseEmitter.event()
                        .name(name)
                        .data(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CustomEmitter getLiar() {
        return liar;
    }

    public void setLiar(Long liarId) {
        this.liar = this.emitterList.stream()
                .filter(emitter -> emitter.getUserId().equals(liarId))
                .findFirst()
                .orElse(null);
        emitterList.remove(liar);
    }

    public CustomEmitter getSpy() {
        return spy;
    }

    public void setSpy(Long spyId) {
        this.spy = this.emitterList.stream()
                .filter(emitter -> emitter.getUserId().equals(spyId))
                .findFirst()
                .orElse(null);
        emitterList.remove(spy);
    }

    public List<Long> getParticipants() {
        return emitterList.stream().map(CustomEmitter::getUserId).collect(Collectors.toList());
    }
}