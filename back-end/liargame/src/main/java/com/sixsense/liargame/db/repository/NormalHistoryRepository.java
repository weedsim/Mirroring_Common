package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.NormalHistory;
import com.sixsense.liargame.db.repository.support.NormalHistoryRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalHistoryRepository extends JpaRepository<NormalHistory, Long>, NormalHistoryRepositorySupport {

}
