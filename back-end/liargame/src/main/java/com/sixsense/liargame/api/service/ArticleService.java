package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.ArticleDetailReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.db.entity.Article;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArticleService {
    Article insertArticle(ArticleDetailReq article);
    List<ArticleResp> getArticles(Pageable pageable);
    void deleteArticle(Long id);
    Long updateArticle(Long id, ArticleDetailReq article);
    Article getArticle(Long id);
}
