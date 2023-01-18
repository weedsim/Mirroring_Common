package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.SpyHistory;
import com.sixsense.liargame.db.repository.support.SpyHistoryRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpyHistoryRepository extends JpaRepository<SpyHistory, Long>, SpyHistoryRepositorySupport {
}
