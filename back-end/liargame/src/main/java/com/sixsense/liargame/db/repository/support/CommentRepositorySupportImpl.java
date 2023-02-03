package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.api.response.CommentResp;
import com.sixsense.liargame.db.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.sixsense.liargame.db.entity.QComment.comment;

@Repository
public class CommentRepositorySupportImpl implements CommentRepositorySupport {
    private final JPAQueryFactory queryFactory;
    public CommentRepositorySupportImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }
    @Override
    public Comment findAllComments(Long articleId, Long commentId) {
        return queryFactory
                .select(comment)
                .from(comment)
                .where(comment.articleId.eq(articleId), comment.id.eq(commentId))
                .orderBy(new OrderSpecifier<>(Order.DESC, comment.updatedAt))
                .<Comment>fetchOne();
    }
    @Override
    public List<Comment> findByArticleId(Long articleId) {
        return queryFactory
                .select(comment)
                .from(comment)
                .where(comment.articleId.eq(articleId))
                .orderBy(new OrderSpecifier<>(Order.DESC, comment.updatedAt))
                .fetch();
    }

//    @Override
//    public List<CommentResp> findAllByArticleId(Long articleId, Sort sort) {
//        return queryFactory
//                .select(comment)
//                .from(comment)
//                .where(comment.articleId.eq(articleId))
//                .fetch(CommentResp.class, sort);
//    }
}
