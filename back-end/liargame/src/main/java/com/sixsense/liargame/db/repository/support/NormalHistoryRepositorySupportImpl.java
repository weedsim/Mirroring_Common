package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.db.entity.NormalHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.sixsense.liargame.db.entity.QNormalHistory.normalHistory;

@Repository
public class NormalHistoryRepositorySupportImpl implements NormalHistoryRepositorySupport {
    private final JPAQueryFactory query;

    public NormalHistoryRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<NormalHistory> findByHistorySet(Set<Long> set) {
        return query
                .select(normalHistory)
                .from(normalHistory)
                .where(normalHistory.id.in(set))
                .orderBy(new OrderSpecifier<>(Order.DESC, normalHistory.id))
                .fetch();
    }
}
