package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.api.service.CommentService;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/comments")
public class CommentController {
    private final CommentService commentService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<?> insertComment(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Long articleId, String content) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.insertComment(userId, articleId, content);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Long commentId, String content) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.updateComment(userId, commentId, content);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResp> deleteComment(@RequestHeader(name = JwtProperties.AUTHORIAZATION) String accessToken, @PathVariable Long commentId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<CommentResp>> findAllComments(@PathVariable Long articleId, Pageable pageable) {
        return ResponseEntity.ok(commentService.findAllComments(articleId, pageable));
    }
}
