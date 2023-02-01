package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;

    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<RoomResp>> getAll(Pageable pageable) {
        List<RoomResp> rooms = roomService.selectAll(pageable);
        return ResponseEntity.ok(rooms);
    }

    @PatchMapping("/{roomId}/enter")
    public ResponseEntity<?> enter(HttpServletRequest request, @PathVariable Long roomId) {
        String accessToken = request.getHeader("access-token");
        String email = jwtTokenProvider.getEmail(accessToken);
        roomId = roomService.enter(email, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}/exit")
    public ResponseEntity<?> exit(HttpServletRequest request, @PathVariable Long roomId) {
        String accessToken = request.getHeader("access-token");
        String email = jwtTokenProvider.getEmail(accessToken);
        roomService.exit(email, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}/start")
    public ResponseEntity<?> start(HttpServletRequest request, @PathVariable Integer roomId) {
        String accessToken = request.getHeader("access-token");
        String email = jwtTokenProvider.getEmail(accessToken);
        roomService.normalStart(email, roomId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{roomId}")
    public ResponseEntity<?> changeSetting(HttpServletRequest request, @PathVariable Integer roomId, @RequestBody SettingDto settingDto) {
        String accessToken = request.getHeader("access-token");
        String email = jwtTokenProvider.getEmail(accessToken);
        settingDto.setId(roomId);
        roomService.changeSetting(email, settingDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomReq roomReq) {
        Integer roomId = roomService.insert(roomReq);
        return ResponseEntity.ok(roomId);
    }
}
