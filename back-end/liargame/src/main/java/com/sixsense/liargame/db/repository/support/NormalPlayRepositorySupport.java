package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.db.entity.NormalPlay;

import java.util.List;

public interface NormalPlayRepositorySupport {
    List<NormalPlay> findTop10ByUserId(Long userId);
}
