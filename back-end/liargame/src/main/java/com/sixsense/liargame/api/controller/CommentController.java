package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.CommentReq;
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
    public ResponseEntity<?> insertComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long articleId,
                                           @RequestBody CommentReq commentReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.insertComment(userId, articleId, commentReq.getContent());
        return ResponseEntity.ok("생성 성공");
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long commentId,
                                           @RequestBody CommentReq commentReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.updateComment(userId, commentId, commentReq.getContent());
        return ResponseEntity.ok("수정 성공");
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long commentId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.ok("삭제 성공");
    }

    @GetMapping
    public ResponseEntity<List<CommentResp>> findAllComments(@PathVariable Long articleId,
                                                             Pageable pageable) {
        return ResponseEntity.ok(commentService.findAllComments(articleId, pageable));
    }
}
