package com.sixsense.liargame.common.model.request;

import com.sixsense.liargame.db.entity.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleDto {
    private String title;
    private String content;
    private Long userId;
    private String userName;
    public Article articleToEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .userId(userId)
                .userName(userName)
                .viewCnt(0)
                .build();
    }
}
