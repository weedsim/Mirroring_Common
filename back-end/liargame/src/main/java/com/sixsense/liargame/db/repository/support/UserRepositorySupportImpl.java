package com.sixsense.liargame.db.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sixsense.liargame.db.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import static com.sixsense.liargame.db.entity.QUser.user;


@Repository
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory query;

    public UserRepositorySupportImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public List<User> findBySet(Set<Long> set) {
        return query
                .select(user)
                .from(user)
                .where(user.id.in(set))
                .fetch();
    }
}
