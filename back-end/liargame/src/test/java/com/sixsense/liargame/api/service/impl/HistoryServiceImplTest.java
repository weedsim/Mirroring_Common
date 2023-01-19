package com.sixsense.liargame.api.service.impl;

import com.sixsense.liargame.api.request.NormalGameHistoryReq;
import com.sixsense.liargame.api.request.SpyGameHistoryReq;
import com.sixsense.liargame.api.response.HistoryResp;
import com.sixsense.liargame.api.service.HistoryService;
import com.sixsense.liargame.common.model.request.GameUserReq;
import com.sixsense.liargame.common.model.response.GameUserResp;
import com.sixsense.liargame.db.entity.*;
import com.sixsense.liargame.db.repository.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HistoryServiceImplTest {

    @Autowired
    HistoryService historyService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NormalHistoryRepository normalHistoryRepository;
    @Autowired
    NormalPlayRepository normalPlayRepository;
    @Autowired
    SpyHistoryRepository spyHistoryRepository;
    @Autowired
    SpyPlayRepository spyPlayRepository;

    List<NormalGameHistoryReq> normalGameList;
    List<SpyGameHistoryReq> spyGameList;
    List<User> userList;
    Long userId = null;
    Long notUserId = null;

    String LIAR = "liar";
    String SPY = "spy";
    String CITIZEN = "citizen";

    @BeforeEach
    void beforeEach() {
        //given
        normalGameList = new ArrayList<>();
        spyGameList = new ArrayList<>();
        userList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            userList.add(
                    User.builder()
                            .name("테스트" + i)
                            .build()
            );
        }
        userId = 100L;
        notUserId = 99L;
        //100명의 유저들 저장
        userRepository.saveAll(userList);

        //노말게임 40개
        long start = 1;
        normalGame(userId, LIAR, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, LIAR, LIAR, start++);
        normalGame(notUserId, LIAR, LIAR, start++);
        normalGame(notUserId, CITIZEN, LIAR, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, LIAR, LIAR, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(userId, LIAR, CITIZEN, start++);
        normalGame(userId, LIAR, CITIZEN, start++);
        normalGame(userId, LIAR, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, LIAR, CITIZEN, start++);
        normalGame(userId, LIAR, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, LIAR, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, CITIZEN, CITIZEN, start++);
        normalGame(userId, LIAR, CITIZEN, start++);//30
        normalGame(notUserId, LIAR, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, LIAR, CITIZEN, start++);
        normalGame(notUserId, LIAR, CITIZEN, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, LIAR, LIAR, start++);
        normalGame(notUserId, LIAR, LIAR, start++);
        normalGame(notUserId, CITIZEN, CITIZEN, start++);
        normalGame(notUserId, LIAR, CITIZEN, start++);//40

        //스파이게임 40개
        spyGame(userId, LIAR, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, SPY, CITIZEN, start++);
        spyGame(userId, SPY, LIAR, start++);
        spyGame(userId, SPY, LIAR, start++);
        spyGame(userId, CITIZEN, LIAR, start++);
        spyGame(notUserId, SPY, LIAR, start++);
        spyGame(notUserId, SPY, LIAR, start++);
        spyGame(notUserId, CITIZEN, LIAR, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, SPY, CITIZEN, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, SPY, CITIZEN, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, LIAR, LIAR, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, LIAR, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, SPY, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, SPY, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, LIAR, LIAR, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, LIAR, CITIZEN, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, SPY, LIAR, start++);
        spyGame(userId, LIAR, LIAR, start++);
        spyGame(userId, CITIZEN, CITIZEN, start++);
        spyGame(userId, LIAR, CITIZEN, start++);//71
        spyGame(notUserId, LIAR, CITIZEN, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, SPY, CITIZEN, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, SPY, LIAR, start++);
        spyGame(notUserId, LIAR, LIAR, start++);
        spyGame(notUserId, CITIZEN, CITIZEN, start++);
        spyGame(notUserId, LIAR, CITIZEN, start);//80
    }

    void normalGame(Long userId, String role, String winner, long start) {
        List<GameUserReq> list = new ArrayList<>();
        list.add(new GameUserReq(userId, role));
        list.add(new GameUserReq(start, CITIZEN));
        list.add(new GameUserReq(start + 1, CITIZEN));
        list.add(new GameUserReq(start + 2, CITIZEN));
        list.add(new GameUserReq(start + 3, CITIZEN));
        list.add(new GameUserReq(start + 4, role.equals(LIAR) ? CITIZEN : LIAR));
        normalGameList.add(new NormalGameHistoryReq(role.equals(LIAR) ? userId : start + 4, winner, list));
    }

    void spyGame(Long userId, String role, String winner, long start) {
        List<GameUserReq> list = new ArrayList<>();
        list.add(new GameUserReq(userId, role));
        list.add(new GameUserReq(start, CITIZEN));
        list.add(new GameUserReq(start + 1, CITIZEN));
        list.add(new GameUserReq(start + 2, CITIZEN));
        list.add(new GameUserReq(start + 3, role.equals(SPY) ? CITIZEN : SPY));
        list.add(new GameUserReq(start + 4, role.equals(LIAR) ? CITIZEN : LIAR));
        spyGameList.add(new SpyGameHistoryReq(role.equals(LIAR) ? userId : start + 4, winner, list, role.equals(SPY) ? userId : start + 3));
    }

    @Test
    @Order(1)
    @DisplayName("1.노말게임 기록 넣었을 때 잘 들어가는지")
    void insertNormalPlay() {
        //when
        for (NormalGameHistoryReq req : normalGameList) {
            historyService.insertNormalPlay(req);
        }
        //then
        List<NormalHistory> normalHistoryList = normalHistoryRepository.findAll();
        List<NormalPlay> normalPlayList = normalPlayRepository.findAll();
        for (long i = 0; i < 40; i++) {
            NormalHistory normalHistory = normalHistoryList.get((int) i);
            assertEquals(normalHistory.getId(), i + 1);
            assertEquals(normalHistory.getWinner(), normalGameList.get((int) i).getWinner());
            assertEquals(normalHistory.getLiar(), normalGameList.get((int) i).getLiar());
            for (long j = 0; j < 6; j++) {
                NormalPlay normalPlay = normalPlayList.get((int) (i * 6 + j));
                assertEquals(normalPlay.getHistoryId(), normalHistory.getId());
                if (j != 0)
                    assertEquals(normalPlay.getUserId(), i + j);
                assertEquals(normalPlay.getRole(), normalGameList.get((int) i).getUsers().get((int) j).getRole());
            }
        }
    }


    @Test
    @Order(2)
    @DisplayName("2.스파이게임 기록 넣었을 때 잘 들어가는지")
    void insertSpyPlay() {
        //when
        for (SpyGameHistoryReq req : spyGameList) {
            historyService.insertSpyPlay(req);
        }
        //then
        List<SpyHistory> spyHistoryList = spyHistoryRepository.findAll();
        List<SpyPlay> spyPlayList = spyPlayRepository.findAll();
        for (long i = 0; i < 40; i++) {
            SpyHistory spyHistory = spyHistoryList.get((int) i);
            assertEquals(spyHistory.getId(), i + 41);
            assertEquals(spyHistory.getWinner(), spyGameList.get((int) i).getWinner());
            assertEquals(spyHistory.getLiar(), spyGameList.get((int) i).getLiar());
            for (long j = 0; j < 6; j++) {
                SpyPlay spyPlay = spyPlayList.get((int) (i * 6 + j));
                assertEquals(spyPlay.getHistoryId(), spyHistory.getId());
                if (j != 0)
                    assertEquals(spyPlay.getUserId(), i + j + 40);
                assertEquals(spyPlay.getRole(), spyGameList.get((int) i).getUsers().get((int) j).getRole());
            }
        }
    }

    @Test
    @Order(3)
    @DisplayName("3.노말게임전적 불러오기")
    void getNormalGameHistory() {
        List<HistoryResp> normalGameHistory = historyService.selectNormalGameHistory(userId);
        int idx = 0;
        HistoryResp historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        List<GameUserResp> users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), CITIZEN));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), CITIZEN));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), CITIZEN));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), LIAR));
        idx++;
        historyResp = normalGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(30L - idx, "테스트" + (30 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(31L - idx, "테스트" + (31 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(32L - idx, "테스트" + (32 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(33L - idx, "테스트" + (33 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(34L - idx, "테스트" + (34 - idx), CITIZEN));

    }

    private String getResult(HistoryResp historyResp, String role) {
        String userRole = historyResp.getRole();
        if (userRole.equals(SPY))
            userRole = LIAR;
        if (userRole.equals(role))
            return "win";
        return "lose";
    }


    @Test
    @Order(4)
    @DisplayName("4.스파이게임전적 불러오기")
    void getSpyGameHistory() {
        List<HistoryResp> spyGameHistory = historyService.selectSpyGameHistory(userId);
        int idx = 0;
        HistoryResp historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        List<GameUserResp> users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), CITIZEN));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), CITIZEN));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), SPY);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), CITIZEN));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), LIAR);
        assertEquals(historyResp.getResult(), getResult(historyResp, LIAR));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), CITIZEN));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), CITIZEN);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), SPY));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
        idx++;
        historyResp = spyGameHistory.get(idx);
        assertEquals(historyResp.getRole(), SPY);
        assertEquals(historyResp.getResult(), getResult(historyResp, CITIZEN));
        users = historyResp.getUsers();
        assertEquals(users.get(0), new GameUserResp(71L - idx, "테스트" + (71 - idx), CITIZEN));
        assertEquals(users.get(1), new GameUserResp(72L - idx, "테스트" + (72 - idx), CITIZEN));
        assertEquals(users.get(2), new GameUserResp(73L - idx, "테스트" + (73 - idx), CITIZEN));
        assertEquals(users.get(3), new GameUserResp(74L - idx, "테스트" + (74 - idx), CITIZEN));
        assertEquals(users.get(4), new GameUserResp(75L - idx, "테스트" + (75 - idx), LIAR));
    }
}
