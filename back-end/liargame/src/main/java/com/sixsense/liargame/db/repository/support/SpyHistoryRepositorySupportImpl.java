package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.db.entity.SpyHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.sixsense.liargame.db.entity.QSpyHistory.spyHistory;


@Repository
public class SpyHistoryRepositorySupportImpl implements SpyHistoryRepositorySupport {
    private final JPAQueryFactory query;

    public SpyHistoryRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<SpyHistory> findByHistorySet(Set<Long> set) {
        return query
                .select(spyHistory)
                .from(spyHistory)
                .where(spyHistory.id.in(set))
                .orderBy(new OrderSpecifier<>(Order.DESC, spyHistory.id))
                .fetch();
    }
}
