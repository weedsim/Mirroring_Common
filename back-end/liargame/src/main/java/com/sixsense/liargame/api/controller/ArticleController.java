package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.api.service.ArticleService;
import com.sixsense.liargame.db.entity.Article;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping
    public ResponseEntity<List<ArticleResp>> getArticles(Pageable pageable) {
        return ResponseEntity.ok(articleService.getArticles(pageable));
    }

//    @GetMapping
//    public ResponseEntity<List<ArticleResp>> getArticles2(@RequestParam(name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10") Integer size, @RequestParam(name = "title", required = false) String title, @RequestParam(name = "writer", required = false) String writer) {
//        PageRequest pagerequest = PageRequest.of(page, size);
//        Pageable pageable = Pageable.ofSize(pagerequest.getPageSize());
//        return ResponseEntity.ok(articleService.getArticles2(page, size, title, writer));
//    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable Long articleId) {
        Article article = articleService.getArticle(articleId);
        return ResponseEntity.ok(article);
    }

    @PostMapping
    public ResponseEntity<?> createArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @RequestBody ArticleReq articleReq) {
        System.out.println(accessToken);
        System.out.println(articleReq);
        Long userId = jwtTokenProvider.getUserId(accessToken);
        System.out.println("accesstoken success");
        articleService.insertArticle(userId, articleReq);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Long articleId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.deleteArticle(userId, articleId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<?> patchArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken, @PathVariable Long articleId, @RequestBody ArticleReq article) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.updateArticle(userId, articleId, article);
        return ResponseEntity.ok().build();
    }
}
