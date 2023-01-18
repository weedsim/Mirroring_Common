package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.db.entity.NormalHistory;

import java.util.List;
import java.util.Set;

public interface NormalHistoryRepositorySupport {
    List<NormalHistory> findByHistorySet(Set<Long> set);
}
