package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.response.HistoryResp;
import com.sixsense.liargame.api.service.HistoryService;
import com.sixsense.liargame.common.model.response.UserDto;
import com.sixsense.liargame.db.entity.*;
import com.sixsense.liargame.db.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final SpyPlayRepository spyPlayRepository;
    private final SpyHistoryRepository spyHistoryRepository;
    private final UserRepository userRepository;

    public HistoryServiceImpl(NormalHistoryRepository normalHistoryRepository, NormalPlayRepository normalPlayRepository, SpyPlayRepository spyPlayRepository, SpyHistoryRepository spyHistoryRepository, UserRepository userRepository) {
        this.normalHistoryRepository = normalHistoryRepository;
        this.normalPlayRepository = normalPlayRepository;
        this.spyPlayRepository = spyPlayRepository;
        this.spyHistoryRepository = spyHistoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<HistoryResp> getSpyGameHistory(Long userId) {
        // 유저들을 중복없이 넣어줄 set
        Set<Long> userSet = new HashSet<>();

        //Key= historyId, Value = UserId List
        Map<Long, List<UserDto>> historyMap = new HashMap<>();

        //result 선언
        Map<Long, HistoryResp> result = new HashMap<>();

        List<SpyPlay> playList = spyPlayRepository.findTop10ByUserId(userId);
        for (SpyPlay spyPlay : playList) {
            userSet.add(spyPlay.getUserId());

            //key가 없다는 것은 객체가 없다는 뜻이기때문에 객체를 만들어서 넣어줌
            if (!result.containsKey(spyPlay.getHistoryId())) {
                HistoryResp normalGame = new HistoryResp();
                normalGame.setRole(spyPlay.getRole());
                result.put(spyPlay.getHistoryId(), new HistoryResp());
            } else { // key가 있으면 historyId에 맞는 NormalGameHistoryResp를 꺼내서 userId, role을 넣어줌
                HistoryResp historyResp = result.get(spyPlay.getHistoryId());
                if (historyResp.getUsers() == null)
                    historyResp.setUsers(new ArrayList<>());
                historyResp.getUsers().add(
                        UserDto.builder()
                                .id(spyPlay.getUserId())
                                .role(spyPlay.getRole())
                                .build());
            }
        }

        //중복없이 모아놨던 유저들을 db에서 가져옴
        List<User> userList = userRepository.findBySet(userSet);

        //user들을 계속 검색해서 가져오기 보다는 user의 id와 userDto를 map으로 매칭시키는게 더 빠름
        Map<Long, String> userMap = new HashMap<>();

        //userMap에 정보들을 집어넣음
        userList.forEach(user -> userMap.put(user.getId(), user.getName()));

        //historyMap의 키값들은 중복되지 않는 historyId들의 집합
        List<SpyHistory> historyList = spyHistoryRepository.findByHistorySet(historyMap.keySet());

        // result에 넣어준다
        for (SpyHistory spyHistory : historyList) {
            HistoryResp spyGame = result.get(spyHistory.getId());
            spyGame.setPlayedTime(spyHistory.getPlayedAt());
            spyGame.setResult(getResult(spyHistory.getWinner(), spyGame.getRole()));
            spyGame.getUsers().forEach(userDto -> userDto.setName(userMap.get(userDto.getId())));
        }
        return result.values().stream().sorted((o1, o2) -> o2.getPlayedTime().compareTo(o1.getPlayedTime())).collect(Collectors.toList());
    }

    @Override
    public List<HistoryResp> getNormalGameHistory(Long userId) {
        // 유저들을 중복없이 넣어줄 set
        Set<Long> userSet = new HashSet<>();

        //Key= historyId, Value = UserId List
        Map<Long, List<UserDto>> historyMap = new HashMap<>();

        //result 선언
        Map<Long, HistoryResp> result = new HashMap<>();

        List<NormalPlay> playList = normalPlayRepository.findTop10ByUserId(userId);
        for (NormalPlay normalPlay : playList) {
            userSet.add(normalPlay.getUserId());

            //key가 없다는 것은 객체가 없다는 뜻이기때문에 객체를 만들어서 넣어줌
            if (!result.containsKey(normalPlay.getHistoryId())) {
                HistoryResp normalGame = new HistoryResp();
                normalGame.setRole(normalPlay.getRole());
                result.put(normalPlay.getHistoryId(), new HistoryResp());
            } else { // key가 있으면 historyId에 맞는 NormalGameHistoryResp를 꺼내서 userId, role을 넣어줌
                HistoryResp historyResp = result.get(normalPlay.getHistoryId());
                if (historyResp.getUsers() == null)
                    historyResp.setUsers(new ArrayList<>());
                historyResp.getUsers().add(
                        UserDto.builder()
                                .id(normalPlay.getUserId())
                                .role(normalPlay.getRole())
                                .build());
            }
        }

        //중복없이 모아놨던 유저들을 db에서 가져옴
        List<User> userList = userRepository.findBySet(userSet);

        //user들을 계속 검색해서 가져오기 보다는 user의 id와 userDto를 map으로 매칭시키는게 더 빠름
        Map<Long, String> userMap = new HashMap<>();

        //userMap에 정보들을 집어넣음
        userList.forEach(user -> userMap.put(user.getId(), user.getName()));

        //historyMap의 키값들은 중복되지 않는 historyId들의 집합
        List<NormalHistory> historyList = normalHistoryRepository.findByHistorySet(historyMap.keySet());

        // result에 넣어준다
        for (NormalHistory normalHistory : historyList) {
            HistoryResp normalGame = result.get(normalHistory.getId());
            normalGame.setPlayedTime(normalHistory.getPlayedAt());
            normalGame.setResult(getResult(normalHistory.getWinner(), normalGame.getRole()));
            normalGame.getUsers().forEach(userDto -> userDto.setName(userMap.get(userDto.getId())));
        }
        return result.values().stream().sorted((o1, o2) -> o2.getPlayedTime().compareTo(o1.getPlayedTime())).collect(Collectors.toList());
    }

    private String getResult(String winner, String role) {
        if (winner.equals("liar") == (role.equals("liar") || role.equals("spy")))
            return "win";
        return "lose";

    }
}
