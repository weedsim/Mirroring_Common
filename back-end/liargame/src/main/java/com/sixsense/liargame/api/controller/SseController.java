package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.SseService;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse")
public class SseController {
    private final SseService sseService;
    private final JwtTokenProvider jwtTokenProvider;

    public SseController(SseService sseService, JwtTokenProvider jwtTokenProvider) {
        this.sseService = sseService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> connect(Integer roomId, @RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        String name = jwtTokenProvider.getUserName(accessToken);
        System.out.println("서비스 전");
        SseEmitter emitter = sseService.connect(roomId, userId, name);
        System.out.println("서비스 후");
        return ResponseEntity.ok(emitter);
    }
}
