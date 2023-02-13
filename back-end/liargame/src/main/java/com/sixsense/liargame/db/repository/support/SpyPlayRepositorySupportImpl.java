package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.db.entity.SpyPlay;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sixsense.liargame.db.entity.QSpyPlay.spyPlay;


@Repository
public class SpyPlayRepositorySupportImpl implements SpyPlayRepositorySupport {
    private final JPAQueryFactory query;

    public SpyPlayRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<SpyPlay> findTop10ByUserId(Long userId) {
        //현재 querydsl로 서브쿼리를 만들면 limit가 적용되지 않는 이슈가 있음
        List<Long> historyIdList = query.select(spyPlay.historyId)
                .from(spyPlay)
                .where(spyPlay.userId.eq(userId))
                .limit(10)
                .orderBy(new OrderSpecifier<>(Order.DESC, spyPlay.historyId))
                .fetch();
        return query
                .select(spyPlay)
                .from(spyPlay)
                .where(spyPlay.historyId.in(historyIdList))
                .fetch();
    }
}
