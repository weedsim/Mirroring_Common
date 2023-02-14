package com.sixsense.liargame.api.sse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Emitters {
    @Getter
    private final List<CustomEmitter> emitterList;
    private final ObjectMapper om;
    private CustomEmitter liar;
    private CustomEmitter spy;

    public Emitters(ObjectMapper om) {
        this.om = om;
        emitterList = new CopyOnWriteArrayList<>();
    }

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

    public void sendToAll(String name, SseResponse sseResponse) {

        sendToCitizens(name, sseResponse);
        if (liar != null)
            sendTo(name, sseResponse, liar);
        if (spy != null)
            sendTo(name, sseResponse, spy);
    }

    public void sendToLiar(String name, SseResponse sseResponse) {
        if (liar != null)
            sendTo(name, sseResponse, liar);
    }

    public void sendToSpy(String name, SseResponse sseResponse) {
        if (spy != null)
            sendTo(name, sseResponse, spy);
    }

    private void sendTo(String name, SseResponse sseResponse, CustomEmitter emitter) {
        String message;
        try {
            message = om.writeValueAsString(sseResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        try {
            emitter.send(SseEmitter.event()
                    .name(name)
                    .data(message));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToCitizens(String name, SseResponse sseResponse) {
        String message;
        try {
            message = om.writeValueAsString(sseResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
}