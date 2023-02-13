package com.sixsense.liargame.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Comment extends CommunityBaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    private String content;
    private Long articleId;
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    private LocalDateTime updatedAt;

    @Builder
    public Comment(Long id, String content, Long articleId, Long userId, User user) {
        this.id = id;
        this.content = content;
        this.articleId = articleId;
        this.userId = userId;
        this.user = user;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateComment(String content) {
        this.content = content;
        updatedAt = LocalDateTime.now();
    }
}
