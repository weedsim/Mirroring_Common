package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.RoomTokenResp;
import com.sixsense.liargame.api.service.RoomService;
import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.api.sse.GlobalEmitter;
import com.sixsense.liargame.common.model.request.RoomReq;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.RoomResp;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.User;
import com.sixsense.liargame.db.repository.NormalHistoryRepository;
import com.sixsense.liargame.db.repository.NormalPlayRepository;
import com.sixsense.liargame.db.repository.RoomRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final String CITIZEN = "citizen";
    private final String LIAR = "liar";
    private final String SPY = "spy";
    private final GlobalEmitter globalEmitter;
    @Value("${openvidu.hostname}")
    private String OPENVIDU_URL;

    @Value("${openvidu.secret}")
    private String OPENVIDU_SECRET;

    private OpenVidu openvidu;

    public RoomServiceImpl(UserRepository userRepository, RoomRepository roomRepository, NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, GlobalEmitter globalEmitter) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.globalEmitter = globalEmitter;
    }

    @PostConstruct
    public void init() {
        this.openvidu = new OpenVidu(OPENVIDU_URL, OPENVIDU_SECRET);
    }

    private RoomTokenResp getToken(Long roomId)
            throws OpenViduJavaClientException, OpenViduHttpException {
        Map<String, Object> map = new HashMap<>();
        map.put("customSessionId", roomId.toString());
        SessionProperties properties = SessionProperties.fromJson(map).build();
        openvidu.createSession(properties);
        return new RoomTokenResp(roomId);
    }

    @Override
    public RoomTokenResp insert(Long userId, RoomReq roomReq) throws OpenViduJavaClientException, OpenViduHttpException {
        Room room = toEntity(roomReq);
        Emitters emitters = room.getEmitters();
        Long roomId = roomRepository.save(room).getId();
        globalEmitter.addEmitters(roomId, emitters);
        return getToken(roomId);
    }

    @Override
    @Transactional
    public void enter(Long userId, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setEmitters(globalEmitter.getEmitters(roomId));
        if (room.getMaxCount() > room.getCurCount()) {
            User user = userRepository.findById(userId).orElseThrow();
            room.enter(user.getId(), user.getName());
        }
    }

    @Override
    @Transactional
    public void exit(Long userId, Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        room.setEmitters(globalEmitter.getEmitters(roomId));
        room.exit(userId);
        if (room.getCurCount() <= 0) {
            roomRepository.delete(room);
            globalEmitter.removeEmitters(roomId);
        }
    }

    @Override
    @Transactional
    public void changeSetting(Long userId, SettingDto settingDto) {
        Room room = roomRepository.findById(settingDto.getId()).orElseThrow();
        if (Objects.equals(userId, room.getMaster()))
            room.changeSetting(settingDto);
    }

    @Override
    public List<RoomResp> selectAll(Pageable pageable) {
        return roomRepository.findAll(pageable).stream().map(this::toDto).collect(Collectors.toList());
    }

}
