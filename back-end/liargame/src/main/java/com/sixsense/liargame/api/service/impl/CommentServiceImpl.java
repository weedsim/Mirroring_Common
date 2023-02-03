package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.api.service.CommentService;
import com.sixsense.liargame.db.entity.Comment;
import com.sixsense.liargame.db.repository.CommentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //댓글 생성
    @Override
    public void insertComment(Long userId, Long articleId, String content) {
        commentRepository.save(toEntity(userId, articleId, content));
    }

    //댓글 삭제
    @Override
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (Objects.equals(comment.getUserId(), userId))
            commentRepository.deleteById(commentId);
    }

    //댓글 업데이트
    @Override
    @Transactional
    public void updateComment(Long userId, Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        if (Objects.equals(comment.getUserId(), userId))
            comment.updateComment(content);
    }

    //게시글의 댓글 전부 가져오기
    @Override
    public List<CommentResp> findAllComments(Long articleId, Pageable pageable) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        PageRequest pageRequest = (PageRequest) pageable;
        pageRequest.withSort(sort);
        List<Comment> comments = commentRepository.findAllByArticleId(articleId, pageRequest);
        return comments.stream().map(CommentResp::new).collect(Collectors.toList());
    }
}
