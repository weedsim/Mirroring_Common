package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.db.entity.Comment;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

public interface CommentRepositorySupport {
    List<Comment> findByArticleId(Long articleId);
    Comment findAllComments(Long articleId, Long commentId);
    List<CommentResp> findAllByArticleId(Long articleId, Sort sort);
}
