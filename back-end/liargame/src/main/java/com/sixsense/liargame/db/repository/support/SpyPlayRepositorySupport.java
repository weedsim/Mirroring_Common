package com.sixsense.liargame.db.repository.support;

import com.sixsense.liargame.db.entity.SpyPlay;

import java.util.List;

public interface SpyPlayRepositorySupport {
    List<SpyPlay> findTop10ByUserId(Long userId);
}
