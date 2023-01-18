package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.support.UserRepositorySupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositorySupport {
}
