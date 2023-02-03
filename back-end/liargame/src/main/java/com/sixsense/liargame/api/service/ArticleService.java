package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.db.entity.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<ArticleResp> getArticles(Pageable pageable);

    Article getArticle(Long articleId);

    void insertArticle(Long userId, ArticleReq articleReq);

    void deleteArticle(Long userId, Long articleId);

    void updateArticle(Long userId, Long articleId, ArticleReq articleReq);

    default Article toEntity(ArticleReq articleReq, Long userId) {
        return Article.builder()
                .title(articleReq.getTitle())
                .content(articleReq.getContent())
                .isNotice(articleReq.getIsNotice())
                .userId(userId)
                .build();
    }
}
