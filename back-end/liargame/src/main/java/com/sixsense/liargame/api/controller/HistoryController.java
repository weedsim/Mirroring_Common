package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.response.HistoryResp;
import com.sixsense.liargame.api.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping("/normal/{userId}")
    public ResponseEntity<List<HistoryResp>> getNormalGame(@PathVariable Long userId) {
        return ResponseEntity.ok().body(historyService.selectNormalGameHistory(userId));
    }

    @GetMapping("/spy/{userId}")
    public ResponseEntity<List<HistoryResp>> getSpyGame(@PathVariable Long userId) {
        return ResponseEntity.ok().body(historyService.selectSpyGameHistory(userId));
    }
}
