package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.NormalPlay;
import com.sixsense.liargame.db.repository.support.NormalPlayRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalPlayRepository extends JpaRepository<NormalPlay, Long>, NormalPlayRepositorySupport {
}
