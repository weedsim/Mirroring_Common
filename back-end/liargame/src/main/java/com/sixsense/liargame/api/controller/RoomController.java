package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.response.RoomResp;
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

    @GetMapping
    public ResponseEntity<List<RoomResp>> getAll(Pageable pageable) {
        List<RoomResp> rooms = roomService.selectAll(pageable);
        return ResponseEntity.ok().body(rooms);
    }

    @PatchMapping("/{id}/enter")
    public ResponseEntity<?> enter(@PathVariable Integer id) {
        roomService.enter(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/exit")
    public ResponseEntity<?> exit(@PathVariable Integer id) {
        roomService.exit(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/start")
    public ResponseEntity<?> start(@PathVariable Integer id) {
        roomService.start(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/end")
    public ResponseEntity<?> end(@PathVariable Integer id) {
        roomService.end(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RoomReq roomReq) {
        roomService.insert(roomReq);
        return ResponseEntity.ok().build();
    }
}
