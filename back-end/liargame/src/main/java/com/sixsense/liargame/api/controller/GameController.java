package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.AnswerReq;
import com.sixsense.liargame.api.service.GameService;
import com.sixsense.liargame.api.sse.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/{roomId}/games")
public class GameController {
    private final GameService gameService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Integer roomId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        GameResultResp gameResultResp = gameService.gameStart(userId, roomId);
        if (gameResultResp != null)
            return ResponseEntity.ok(gameResultResp);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @PostMapping("/vote")
    public ResponseEntity<?> vote(@PathVariable Integer roomId, @RequestBody Vote vote) {
        gameService.vote(vote, roomId);
        return ResponseEntity.ok(vote);
    }

    @PostMapping("/answer")
    public ResponseEntity<?> insertAnswer(@PathVariable Integer roomId, @RequestBody AnswerReq answer) {
        answer.setRoomId(roomId);
        gameService.insertAnswer(answer);
        return ResponseEntity.ok(answer);
    }
}
