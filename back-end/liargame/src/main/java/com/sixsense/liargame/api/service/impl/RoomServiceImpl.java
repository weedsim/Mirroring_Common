package com.sixsense.liargame.api.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.api.sse.SseResponse;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomDetail;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final UserRepository userRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final int MAX_ROOM_SIZE = 200;
    private final ObjectMapper om;
    private Map<Integer, Room> rooms;


    public RoomServiceImpl(UserRepository userRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GlobalRoom globalRoom, ObjectMapper om) {
        this.userRepository = userRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.rooms = globalRoom.getRooms();
        this.om = om;
    }

    private Integer getRoomId() {
        for (int i = 1; i < MAX_ROOM_SIZE; i++) {
            if (!rooms.containsKey(i))
                return i;
        }
        return null;
    }

    @Override
    public RoomDetail insert(Long userId, RoomReq roomReq) {
        Room room = toEntity(roomReq);
        room.setEmitters(new Emitters(om));
        Integer roomId = getRoomId();
        rooms.put(roomId, room);
        room.setMaster(userId);
        room.setId(roomId);
        enter(userId, roomId);
        return toDetail(room);
    }

    @Override
    public RoomDetail enter(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);
        if (6 > room.getCurCount()) {
            User user = userRepository.findById(userId).orElseThrow();
            room.enter(user.getId(), user.getName());
            RoomDetail roomDetail = toDetail(room);
            try {
                room.getEmitters().sendToAll("message", new SseResponse("room", om.writeValueAsString(roomDetail)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            return roomDetail;
        }
        return null;
    }

    @Override
    public void exit(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);
        room.exit(userId);

        if (room.getCurCount() <= 0) {
            rooms.remove(roomId);
        }

        RoomDetail roomDetail = toDetail(room);
        try {
            room.getEmitters().sendToAll("message", new SseResponse("message", om.writeValueAsString(roomDetail)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changeSetting(Long userId, SettingDto settingDto) {
        Room room = rooms.get(settingDto.getId());
        if (Objects.equals(userId, room.getMaster()))
            room.changeSetting(settingDto);
    }

    @Override
    public List<RoomResp> selectAll(Pageable pageable) {
        System.out.println("방목록 요청");
        System.out.println(pageable.getPageNumber());
        List<RoomResp> list = rooms.values().stream().map(this::toDto).sorted(Comparator.comparingInt(RoomResp::getId)).skip(pageable.getPageNumber() * 10L).limit(10).collect(Collectors.toList());
        System.out.println(list.toString());
        return list;
    }

    @Override
    public Integer last() {
        return (rooms.size() - 1) / 10 + 1;
    }

    @Override
    public void ready(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);
        Integer ready = room.ready(userId);
        if (ready != null) {
            boolean isReady = room.getParticipants().get(ready).getIsReady();
            if (isReady) {
                room.getEmitters().sendToAll("message", new SseResponse("ready", ready.toString()));
                return;
            }
            room.getEmitters().sendToAll("message", new SseResponse("unready", ready.toString()));
        }
    }

}
