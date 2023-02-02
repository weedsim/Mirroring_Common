package com.sixsense.liargame.common.model.response;

import com.sixsense.liargame.db.entity.Article;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResp {
    private Long id;
    private String title;
    private String content;
    private Boolean isNotice;
    private Long userId;
    private String userName;
    private Integer viewCnt;
    private LocalDateTime updatedAt;

    public ArticleResp(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.isNotice = article.getIsNotice();
        this.userId = article.getUserId();
        this.userName = article.getUserName();
        this.viewCnt = article.getViewCnt();
        this.updatedAt = article.getUpdatedAt();
    }
}
