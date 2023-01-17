package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.NormalGameHistoryReq;
import com.sixsense.liargame.api.request.SpyGameHistoryReq;
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
        //header에서 어떤 보안규칙을 통과한 이후에만 밑의 코드를 진행
        historyService.insertNormalPlay(history);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/spy")
    public ResponseEntity<?> writeSpy(HttpServletRequest request, @RequestBody SpyGameHistoryReq history) {
        //header에서 어떤 보안규칙을 통과한 이후에만 밑의 코드를 진행
        historyService.insertSpyPlay(history);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/normal/{userId}")
    public ResponseEntity<List<HistoryResp>> getNormalGame(@PathVariable Long userId) {
        return ResponseEntity.ok().body(historyService.getNormalGameHistory(userId));
    }

    @GetMapping("/spy/{userId}")
    public ResponseEntity<List<HistoryResp>> getSpyGame(@PathVariable Long userId) {
        return ResponseEntity.ok().body(historyService.getSpyGameHistory(userId));
    }
}
