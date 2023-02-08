package com.sixsense.liargame.db.entity;

//import com.sixsense.liargame.db.entity.User;

import com.sixsense.liargame.api.request.ArticleReq;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Getter
@ToString
public class Article extends CommunityBaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String content;
    private Boolean isNotice;
    private String username;
    @Column(name = "user_id")
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @ColumnDefault("0")
    private Integer viewCnt;
    private LocalDateTime updatedAt;

    @Builder
    public Article(Long id, String title, String content, Boolean isNotice, Long userId, Integer viewCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        this.userId = userId;
        this.viewCnt = viewCnt;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateArticle(ArticleReq articleReq) {
        this.title = articleReq.getTitle();
        this.content = articleReq.getContent();
        this.isNotice = articleReq.getIsNotice();
        this.updatedAt = LocalDateTime.now();
    }

    public void updateViewCnt() {
        viewCnt++;
    }
}
