package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.response.RoomTokenResp;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<RoomResp>> getAll(Pageable pageable) {
        List<com.sixsense.liargame.common.model.response.RoomResp> rooms = roomService.selectAll(pageable);
        return ResponseEntity.ok(rooms);
    }

    @PatchMapping("/{roomId}/enter")
    public ResponseEntity<?> enter(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.enter(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}/exit")
    public ResponseEntity<?> exit(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.exit(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<?> changeSetting(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Integer roomId, @RequestBody SettingDto settingDto) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        settingDto.setId(roomId);
        roomService.changeSetting(userId, settingDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<RoomTokenResp> create(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @RequestBody RoomReq roomReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        RoomTokenResp roomTokenResp;
        try {
            roomTokenResp = roomService.insert(userId, roomReq);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(roomTokenResp);
    }
}
