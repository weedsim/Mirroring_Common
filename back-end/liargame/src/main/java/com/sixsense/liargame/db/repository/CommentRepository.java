package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.Comment;
import com.sixsense.liargame.db.repository.support.CommentRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositorySupport {
}
