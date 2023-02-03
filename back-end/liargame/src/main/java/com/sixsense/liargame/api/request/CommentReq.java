package com.sixsense.liargame.api.request;

import com.sixsense.liargame.db.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentReq {
    private String commentContent;
    private Long userId;
    private String userName;
    private Long articleId;
    private LocalDateTime updatedAt;

    public Comment commentToEntity(Long id){
        return Comment.builder()
                .commentContent(commentContent)
                .userId(userId)
                .userName(userName)
                .articleId(id)
                .build();
    }
}
