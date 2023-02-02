package com.sixsense.liargame.db.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

import static com.sixsense.liargame.db.entity.QArticle.article;

@Repository
public class ArticleRepositorySupportImpl implements ArticleRepositorySupport {
    private final JPAQueryFactory query;
    public ArticleRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }
    @Override
    @Transactional
    public void updateViewCnt(Long articleId) {
        Integer ViewCnt = query.select(article.viewCnt).from(article).where(article.id.eq(articleId)).fetchOne();
        query.update(article)
                .set(article.viewCnt, ViewCnt + 1)
                .where(article.id.eq(articleId))
                .execute();
    }
}
