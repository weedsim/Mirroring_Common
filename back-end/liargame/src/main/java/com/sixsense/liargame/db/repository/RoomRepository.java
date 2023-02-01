package com.sixsense.liargame.db.repository;

import com.sixsense.liargame.db.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {
    List<Room> findAll(Pageable pageable);
}
