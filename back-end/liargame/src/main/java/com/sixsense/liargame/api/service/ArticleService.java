package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.db.entity.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    List<ArticleResp> getArticles(Integer page, Integer size, String title, String writer);
    ArticleResp getArticle(Long articleId);

    void insertArticle(Long userId, String username, ArticleReq articleReq);

    void deleteArticle(Long userId, Long articleId);

    void updateArticle(Long userId, Long articleId, ArticleReq articleReq);

    default Article toEntity(ArticleReq articleReq, Long userId, String userName) {
        return Article.builder()
                .title(articleReq.getTitle())
                .content(articleReq.getContent())
                .isNotice(articleReq.getIsNotice())
                .viewCnt(1)
                .userId(userId)
                .userName(userName)
                .build();
    }
}
