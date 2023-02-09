package com.sixsense.liargame.api.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.sixsense.liargame.db.entity.Article;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String createdAt;
    private String updatedAt;

    public ArticleResp(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.isNotice = article.getIsNotice();
        this.userName = article.getUser().getName();
        this.viewCnt = article.getViewCnt();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.createdAt = article.getCreatedAt().format(dateTimeFormatter);
        this.updatedAt = article.getUpdatedAt().format(dateTimeFormatter);
    }
}
