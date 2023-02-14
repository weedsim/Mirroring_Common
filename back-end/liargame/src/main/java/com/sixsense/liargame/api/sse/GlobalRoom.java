package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.db.entity.Room;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class GlobalRoom {
    private final Map<Integer, Room> rooms;
    private final Map<Integer, Game> games;

    public GlobalRoom() {
        this.games = new ConcurrentHashMap<>();
        this.rooms = new ConcurrentHashMap<>();
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public Map<Integer, Game> getGames() {
        return games;
    }
}
