package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.repository.support.SpyPlayRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpyPlayRepository extends JpaRepository<NormalPlay, Long>, SpyPlayRepositorySupport {
}
