package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.CommentReq;
import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.api.service.CommentService;
import com.sixsense.liargame.db.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles/{articleId}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public Comment insertComment(@PathVariable Long articleId, @RequestBody CommentReq commentReq) {
        return commentService.insertComment(articleId, commentReq);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long articleId, @PathVariable Long commentId, @RequestBody CommentReq commentReq) {
        commentService.updateComment(articleId, commentId, commentReq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentResp> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<CommentResp> findAllComments(@PathVariable Long articleId) {
        return commentService.findAllComments(articleId);
    }
}
