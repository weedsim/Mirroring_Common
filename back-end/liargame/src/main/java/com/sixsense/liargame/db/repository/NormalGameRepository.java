package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.api.sse.NormalGame;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NormalGameRepository extends CrudRepository<NormalGame, Long> {
    Optional<NormalGame> findById(Long id);
}
