package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.api.service.ArticleService;
import com.sixsense.liargame.db.entity.Article;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@Log4j
public class ArticleController {
    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<ArticleResp>> getArticles(Pageable pageable) {
        return ResponseEntity.ok(articleService.getArticles(pageable));
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable Long articleId) {
        ArticleResp articleResp = articleService.getArticle(articleId);
        return ResponseEntity.ok(articleResp);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @RequestBody ArticleReq articleReq) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.insertArticle(userId, articleReq);
        return ResponseEntity.ok("성공");
    }


    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long articleId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.deleteArticle(userId, articleId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<?> patchArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                          @PathVariable Long articleId,
                                          @RequestBody ArticleReq article) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.updateArticle(userId, articleId, article);
        return ResponseEntity.ok("성공");
    }
}
