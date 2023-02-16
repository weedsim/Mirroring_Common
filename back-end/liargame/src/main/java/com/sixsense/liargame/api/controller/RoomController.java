package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.RoomReq;
import com.sixsense.liargame.api.response.RoomResp;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.common.model.RoomDetail;
import com.sixsense.liargame.common.model.SettingDto;
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
public class RoomController {
    private final RoomService roomService;

    private final JwtTokenProvider jwtTokenProvider;
    private final GlobalRoom globalRoom;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomResp>> getAll(Pageable pageable) {
        List<RoomResp> rooms = roomService.selectAll(pageable);
        return ResponseEntity.ok(rooms);
    }

    @PatchMapping("/sse/rooms/{roomId}/enter")
    public ResponseEntity<RoomDetail> enter(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        RoomDetail roomDetail = roomService.enter(userId, roomId);
        if (roomDetail == null)
            return ResponseEntity.internalServerError().build();
        return ResponseEntity.ok(roomDetail);
    }

    @PatchMapping("/sse/rooms/{roomId}/ready")
    public ResponseEntity<RoomDetail> ready(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.ready(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/sse/rooms/{roomId}/exit")
    public ResponseEntity<?> exit(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.exit(userId, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/sse/rooms/{roomId}/ban/{banId}")
    public ResponseEntity<?> ban(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId, @PathVariable Long banId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        roomService.ban(userId, roomId, banId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/rooms/{roomId}")
    public ResponseEntity<?> changeSetting(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId, @RequestBody SettingDto settingDto) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        System.out.println(userId);
        settingDto.setId(roomId);
        roomService.changeSetting(userId, settingDto);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/rooms")
    public ResponseEntity<RoomDetail> create(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @RequestBody RoomReq roomReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        RoomDetail roomDetail;
        try {
            roomDetail = roomService.insert(userId, roomReq);
        } catch (OpenViduJavaClientException | OpenViduHttpException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(roomDetail);
    }

    @GetMapping("/rooms/last")
    public ResponseEntity<Integer> lastNumber() {
        return ResponseEntity.ok(roomService.last());
    }
}
