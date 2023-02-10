package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Page<Article> findAll(Pageable pageable);
    @Query("SELECT a FROM Article a WHERE a.title LIKE %:title%")
    Page<Article> findByTitle(@Param("title")String title, Pageable pageable);

    @Query("SELECT a FROM Article a WHERE a.userName LIKE %:writer%")
    Page<Article> findByUserName(@Param("writer")String writer, Pageable pageable);
}
