package com.sixsense.liargame.api.response;

import com.sixsense.liargame.db.entity.Article;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleResp {
    private Long id;
    private String title;
    private String content;
    private Boolean isNotice;
    private String userName;
    private Integer viewCnt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ArticleResp(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.isNotice = article.getIsNotice();
        this.userName = article.getUser().getName();
        this.viewCnt = article.getViewCnt();
        this.createdAt = article.getCreatedAt();
    }
}
