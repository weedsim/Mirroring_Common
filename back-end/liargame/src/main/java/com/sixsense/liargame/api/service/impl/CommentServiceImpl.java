package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.request.CommentReq;
import com.sixsense.liargame.api.response.ArticleResp;
import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.api.service.CommentService;
import com.sixsense.liargame.db.entity.Comment;
import com.sixsense.liargame.db.repository.CommentRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    //댓글 생성
    @Override
    public Comment insertComment(Long articleId, CommentReq commentReq) {
        Comment comment = commentRepository.save(commentReq.commentToEntity(articleId));
        return comment;
    }

    //댓글 삭제
    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    //댓글 업데이트
    @Override
    public Long updateComment(Long articleId, Long commentId, CommentReq commentReq){
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.updateComment(commentReq.getCommentContent());
        commentRepository.save(comment);
        return commentId;
    }

    //게시글의 댓글 전부 가져오기
    @Override
    public List<CommentResp> findAllComments(Long articleId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream().map(CommentResp::new).collect(Collectors.toList());
    }
}
