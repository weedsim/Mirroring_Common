package com.sixsense.liargame.api.response;

import com.sixsense.liargame.common.model.response.ArticleResp;

import com.sixsense.liargame.db.entity.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResp {
    private Long id;
    private String commentContent;
    private Long userId;
    private String userName;
    private Long articleId;
    private LocalDateTime updatedAt;

    public CommentResp(Comment comment) {
        this.id = comment.getId();
        this.commentContent = comment.getCommentContent();
        this.userId = comment.getUserId();
        this.userName = comment.getUserName();
        this.articleId = comment.getArticleId();
        this.updatedAt = comment.getUpdatedAt();
    }
}
