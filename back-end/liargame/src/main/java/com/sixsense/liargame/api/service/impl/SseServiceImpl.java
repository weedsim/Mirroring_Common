package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.service.SseService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.common.model.CustomEmitter;
import com.sixsense.liargame.db.entity.Room;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SseServiceImpl implements SseService {
    private final Map<Integer, Room> rooms;

    public SseServiceImpl(GlobalRoom globalRoom) {
        rooms = globalRoom.getRooms();
    }

    @Override
    public CustomEmitter connect(Integer roomId, Long userId, String name) {
        Room room = rooms.get(roomId);
        Emitters emitters = room.getEmitters();
        CustomEmitter emitter = new CustomEmitter(userId, 1000 * 60 * 30L, name);
        emitters.add(emitter);
        System.out.println("emitter 생성완료");
        emitters.sendToAll("message", userId.toString() + "님이 입장하셨습니다.");
        System.out.println("메세지 보내기");
        return emitter;
    }
}