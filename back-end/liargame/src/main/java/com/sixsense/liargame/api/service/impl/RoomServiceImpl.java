package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.RoomTokenResp;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.GlobalRoom;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.UserRepository;
import io.openvidu.java.client.OpenVidu;
import io.openvidu.java.client.OpenViduHttpException;
import io.openvidu.java.client.OpenViduJavaClientException;
import io.openvidu.java.client.SessionProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
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
    @Value("${openvidu.hostname}")
    private String OPENVIDU_URL;
    @Value("${openvidu.secret}")
    private String OPENVIDU_SECRET;
    private OpenVidu openvidu;
    private Map<Integer, Room> rooms;

    public RoomServiceImpl(UserRepository userRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GlobalRoom globalRoom) {
        this.userRepository = userRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.rooms = globalRoom.getRooms();
    }

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    private RoomTokenResp getToken(Integer roomId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Map<String, Object> map = new HashMap<>();
        map.put("customSessionId", roomId.toString());
        SessionProperties properties = SessionProperties.fromJson(map).build();
        openvidu.createSession(properties);
        return new RoomTokenResp(roomId);
    }

    private Integer getRoomId() {
        for (int i = 1; i < MAX_ROOM_SIZE; i++) {
            if (!rooms.containsKey(i))
                return i;
        }
        return null;
    }

    @Override
    public RoomTokenResp insert(Long userId, RoomReq roomReq) throws OpenViduJavaClientException, OpenViduHttpException {
        Room room = toEntity(roomReq);
        Integer roomId = getRoomId();
        room.setId(roomId);
        return getToken(roomId);
    }

    @Override
    public void enter(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);
        if (room.getMaxCount() > room.getCurCount()) {
            User user = userRepository.findById(userId).orElseThrow();
            room.enter(user.getId(), user.getName());
        }
    }

    @Override
    @Transactional
    public void exit(Long userId, Integer roomId) {
        Room room = rooms.get(roomId);
        room.exit(userId);
        if (room.getCurCount() <= 0) {
            rooms.remove(roomId);
        }
    }

    @Override
    @Transactional
    public void changeSetting(Long userId, SettingDto settingDto) {
        Room room = rooms.get(settingDto.getId());
        if (Objects.equals(userId, room.getMaster()))
            room.changeSetting(settingDto);
    }

    @Override
    public List<RoomResp> selectAll(Pageable pageable) {
        return rooms.values().stream().map(this::toDto).sorted(Comparator.comparingInt(RoomResp::getId)).limit(8).collect(Collectors.toList());
    }

}
