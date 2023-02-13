package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.db.entity.SpyHistory;

import java.util.List;
import java.util.Set;

public interface SpyHistoryRepositorySupport {
    List<SpyHistory> findByHistorySet(Set<Long> set);
}
