package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.support.UserRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByName(String name);
}
