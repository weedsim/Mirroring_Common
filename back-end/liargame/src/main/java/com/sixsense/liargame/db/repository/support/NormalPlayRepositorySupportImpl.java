package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.db.entity.NormalPlay;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sixsense.liargame.db.entity.QNormalPlay.normalPlay;

@Repository
public class NormalPlayRepositorySupportImpl implements NormalPlayRepositorySupport {
    private final JPAQueryFactory query;

    public NormalPlayRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<NormalPlay> findTop10ByUserId(Long userId) {
        return query
                .select(normalPlay)
                .from(normalPlay)
                .where(normalPlay.historyId.in(
                        JPAExpressions
                                .select(normalPlay.historyId)
                                .from(normalPlay)
                                .where(normalPlay.userId.eq(userId))
                                .limit(10)
                                .orderBy(new OrderSpecifier<>(Order.DESC, normalPlay.historyId))
                ))
                .fetch();
    }
}
