package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.request.CommentReq;
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

    @Override
    public Comment insertComment(Long articleId, CommentReq commentReq) {
        Comment comment = commentRepository.save(commentReq.commentToEntity(articleId));
        return comment;
    }

    @Override
    public List<CommentResp> findAllComments(Long articleId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updatedAt");
        List<Comment> comments = commentRepository.findAllByArticleId(articleId, sort);
        return comments.stream().map(CommentResp::new).collect(Collectors.toList());
    }
}
