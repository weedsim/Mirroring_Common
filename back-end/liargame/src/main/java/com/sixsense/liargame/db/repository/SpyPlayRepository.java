package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.SpyPlay;
import com.sixsense.liargame.db.repository.support.SpyPlayRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpyPlayRepository extends JpaRepository<SpyPlay, Long>, SpyPlayRepositorySupport {
}
