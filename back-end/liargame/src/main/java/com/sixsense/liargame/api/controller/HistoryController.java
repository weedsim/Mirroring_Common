package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.response.HistoryResp;
import com.sixsense.liargame.api.service.HistoryService;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/history")
public class HistoryController {
    private final HistoryService historyService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/normal")
    public ResponseEntity<List<HistoryResp>> getNormalGame(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        return ResponseEntity.ok().body(historyService.selectNormalGameHistory(userId));
    }

    @GetMapping("/spy")
    public ResponseEntity<List<HistoryResp>> getSpyGame(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        return ResponseEntity.ok().body(historyService.selectSpyGameHistory(userId));
    }
}
