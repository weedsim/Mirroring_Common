package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.request.ArticleReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.api.service.ArticleService;
import com.sixsense.liargame.security.auth.JwtProperties;
import com.sixsense.liargame.security.auth.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<List<ArticleResp>> getArticles(@RequestParam(value = "page", defaultValue = "0")Integer page,
                                                         @RequestParam(value = "size", defaultValue = "10")Integer size,
                                                         @RequestParam(value = "title", required = false)String title,
                                                         @RequestParam(value = "writer", required = false)String writer) {
        
        return ResponseEntity.ok(articleService.getArticles(page, size, title, writer));
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
        String userName = jwtTokenProvider.getUserName(accessToken);
        articleService.insertArticle(userId, userName, articleReq);
        return ResponseEntity.ok("INSERT SUCCESS");
    }


    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                           @PathVariable Long articleId) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.deleteArticle(userId, articleId);
        return ResponseEntity.ok("DELETE SUCCESS");
    }

    @PatchMapping("/{articleId}")
    public ResponseEntity<?> patchArticle(@RequestHeader(name = JwtProperties.AUTHORIZATION) String accessToken,
                                          @PathVariable Long articleId,
                                          @RequestBody ArticleReq article) {
        Long userId = jwtTokenProvider.getUserId(accessToken);
        articleService.updateArticle(userId, articleId, article);
        return ResponseEntity.ok("UPDATE SUCCESS");
    }
}
