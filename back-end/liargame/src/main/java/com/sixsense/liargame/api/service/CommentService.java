package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.db.entity.Comment;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    void insertComment(Long userId, Long articleId, String content);

    void deleteComment(Long userId, Long commentId);

    void updateComment(Long userId, Long commentId, String content);

    List<CommentResp> findAllComments(Long articleId, Pageable pageable);

    default Comment toEntity(Long userId, Long articleId, String content) {
        return Comment.builder()
                .articleId(articleId)
                .userId(userId)
                .content(content)
                .build();
    }
}
