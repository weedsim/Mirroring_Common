package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.api.service.ArticleService;
import com.sixsense.liargame.db.entity.Article;
import com.sixsense.liargame.db.repository.ArticleRepository;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public ArticleResp getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.updateViewCnt();
        return new ArticleResp(article);
    }

    @Override
    public void insertArticle(Long userId, String userName, ArticleReq articleReq) {
        articleRepository.save(toEntity(articleReq, userId, userName));
    }


    @Override
    public void deleteArticle(Long userId, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        if (Objects.equals(article.getUserId(), userId))
            articleRepository.delete(article);
    }

    @Override
    @Transactional
    public void updateArticle(Long userId, Long articleId, ArticleReq articleReq) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        if (Objects.equals(article.getUserId(), userId)) {
            article.updateArticle(articleReq);
        }
    }

    @Override
    public List<ArticleResp> getArticles(Integer page, Integer size, String title, String writer) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Article> articles = articleRepository.findAll(pageable);
        if(title!= null && !title.isEmpty()){
            articles = articleRepository.findByTitle(title, pageable);
        }
        else if(writer!= null && !writer.isEmpty()){
            articles = articleRepository.findByUserName(writer, pageable);
        }
        else {
            articles = articleRepository.findAll(pageable);
        }
        return articles.stream().map(ArticleResp::new).collect(Collectors.toList());
    }
}
