package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.NormalGameHistoryReq;
import com.sixsense.liargame.api.response.HistoryResp;
import com.sixsense.liargame.api.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;

    @PostMapping("/normal")
    public ResponseEntity<?> writeNormal(HttpServletRequest request, @RequestBody NormalGameHistoryReq history) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/spy")
    public ResponseEntity<?> writeSpy(HttpServletRequest request, @RequestBody NormalGameHistoryReq history) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/normal/{id}")
    public ResponseEntity<List<HistoryResp>> getNormalGame(@PathVariable Long id) {
        return ResponseEntity.ok().body(historyService.getNormalGameHistory(id));
    }

    @GetMapping("/spy/{id}")
    public ResponseEntity<List<HistoryResp>> getSpyGame(@PathVariable Long id) {
        return ResponseEntity.ok().body(historyService.getSpyGameHistory(id));
    }
}
