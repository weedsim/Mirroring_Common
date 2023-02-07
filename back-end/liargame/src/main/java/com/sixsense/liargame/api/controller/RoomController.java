package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomDetail;
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
    private final GlobalRoom globalRoom;

    @GetMapping
    public ResponseEntity<List<RoomResp>> getAll(Pageable pageable) {
        List<RoomResp> rooms = roomService.selectAll(pageable);
        return ResponseEntity.ok(rooms);
    }

    @PatchMapping("/{roomId}/enter")
    public ResponseEntity<RoomDetail> enter(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        RoomDetail roomDetail = roomService.enter(userId, roomId);
        if (roomDetail == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(roomDetail);
    }

    @PatchMapping("/{roomId}/ready")
    public ResponseEntity<RoomDetail> ready(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.ready(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}/exit")
    public ResponseEntity<?> exit(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.exit(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<?> changeSetting(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId, @RequestBody SettingDto settingDto) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        System.out.println(userId);
        settingDto.setId(roomId);
        roomService.changeSetting(userId, settingDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @RequestBody RoomReq roomReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        Integer roomId;
        try {
            roomId = roomService.insert(userId, roomReq);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(roomId);
    }

    @GetMapping("/last")
    public ResponseEntity<Integer> lastNumber() {
        return ResponseEntity.ok(roomService.last());
    }
}
