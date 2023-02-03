package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId, Pageable pageable);
}
