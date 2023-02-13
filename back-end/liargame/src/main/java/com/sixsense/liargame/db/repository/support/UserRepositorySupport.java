package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.db.entity.User;

import java.util.List;
import java.util.Set;

public interface UserRepositorySupport {
    List<User> findBySet(Set<Long> set);
}
