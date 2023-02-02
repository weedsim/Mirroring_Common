package com.sixsense.liargame.common.model.request;

import com.sixsense.liargame.db.entity.Comment;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long userId;
    private String commentContent;
    private Long articleId;
    private String userName;

    public Comment commentToEntity() {
        return Comment.builder()
                .commentContent(commentContent)
                .userId(userId)
                .articleId(articleId)
                .userName(userName)
                .build();
    }
}
