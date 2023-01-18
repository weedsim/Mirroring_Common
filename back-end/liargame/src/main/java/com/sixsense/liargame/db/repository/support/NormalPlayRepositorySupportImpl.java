package com.sixsense.liargame.db.repository.support;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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
        //현재 querydsl로 서브쿼리를 만들면 limit가 적용되지 않는 이슈가 있음
        List<Long> historyIdList = query.select(normalPlay.historyId)
                .from(normalPlay)
                .where(normalPlay.userId.eq(userId))
                .limit(10)
                .orderBy(new OrderSpecifier<>(Order.DESC, normalPlay.historyId))
                .fetch();
        return query
                .select(normalPlay)
                .from(normalPlay)
                .where(normalPlay.historyId.in(historyIdList))
                .fetch();
    }
}
