package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.CommentReq;
import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.api.service.CommentService;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<?> insertComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long articleId, String content) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.insertComment(userId, articleId, content);
        return ResponseEntity.ok("INSERT SUCCESS");
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long commentId, String content) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.updateComment(userId, commentId, content);
        return ResponseEntity.ok("UPDATE SUCCESS");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<> deleteComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                                     @PathVariable Long commentId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.ok("DELETE SUCCESS");
    }

    @GetMapping
    public ResponseEntity<List<CommentResp>> findAllComments(@PathVariable Long articleId,
                                                             @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "5") Integer size) {
        return ResponseEntity.ok(commentService.findAllComments(articleId, page, size));
    }
}
