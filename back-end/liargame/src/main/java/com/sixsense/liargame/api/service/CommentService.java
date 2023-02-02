package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.CommentReq;
import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.db.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment insertComment(Long articleId, CommentReq commentReq);
    void deleteComment(Long id);
    Long updateComment(Long articleId, Long commentId, CommentReq commentReq);
    List<CommentResp> findAllComments(Long articleId);
}
