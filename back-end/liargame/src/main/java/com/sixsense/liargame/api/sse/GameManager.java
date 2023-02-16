package com.sixsense.liargame.api.sse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sixsense.liargame.common.model.GameInfo;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.entity.SpyGame;
import com.sixsense.liargame.db.entity.Vote;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GameManager {
    private final int SPARE_TIME = 200;
    private final Map<Integer, Game> games;
    private final ObjectMapper om;

    public GameManager(GlobalRoom globalRoom, ObjectMapper om) {
        this.games = globalRoom.getGames();
        this.om = om;
    }

    public Game start(Room room) throws InterruptedException {
        Game game = games.get(room.getId());
        Emitters emitters = room.getEmitters();
        int timeout = room.getTimeout();

        emitters.setLiar(game.getLiarUserId());
        if (room.getMode().equals("spy"))
            emitters.setSpy(((SpyGame) game).getSpyUserId());
        GameInfo citizenInfo = new GameInfo(game.getSubject(), game.getWord(), "citizen");
        GameInfo liarInfo = new GameInfo(game.getSubject(), null, "liar");
        GameInfo spyInfo = new GameInfo(game.getSubject(), game.getWord(), "spy");

        try {
            emitters.sendToCitizens("message", new SseResponse("role", om.writeValueAsString(citizenInfo)));
            emitters.sendToLiar("message", new SseResponse("role", om.writeValueAsString(liarInfo)));
            emitters.sendToSpy("message", new SseResponse("role", om.writeValueAsString(spyInfo)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        emitters.sendToAll("message", new SseResponse("message", "game start"));
        Thread.sleep(2000);
        //timeout 만큼 쉼
        waitTimeout(room.getEmitters(), timeout * 1000);
        //차례대로 발언
        Integer curSpeaker = game.getCurSpeaker();
        while (curSpeaker != null) {

            emitters.sendToAll("message", new SseResponse("curSpeaker", curSpeaker.toString()));
            waitTimeout(room.getEmitters(), timeout * 1000);
            curSpeaker = game.changeSpeaker();
        }
        //투표시간 알림
        noticeVote(emitters);
        //투표 집계 후 투표 결과 알림 동표일경우 5번까지 투표 진행
        List<Vote> votes = game.getVotes();
        Integer result = getVoteResult(votes);
        int voteCount = 1;
        while (result == null && voteCount++ < 5) {
            noticeVote(emitters);
            result = getVoteResult(votes);
        }
        //시민인 경우 바로 게임종료, 라이어인 경우 정답 입력 시간 알림
        String winner = "LIAR";
        if (Objects.equals(game.getLiar(), result)) {
            emitters.sendToCitizens("message", new SseResponse("message", "selected liar"));
            emitters.sendToLiar("message", new SseResponse("message", "write answer"));
            waitTimeout(room.getEmitters(), 10 * 1000); // 정답 입력시간 10초
            if (!StringUtils.hasText(game.getAnswer())) {
                winner = "CITIZEN";
            }
            if (StringUtils.hasText(game.getAnswer()) && !game.getAnswer().equals(game.getWord()))
                winner = "CITIZEN";
        } else if (game instanceof SpyGame && Objects.equals(((SpyGame) game).getSpy(), result)) {
            winner = "CITIZEN";
        }
        game.setWinner(winner);
        System.out.println("winner: " + winner);

        return game;
        //정답여부와 승패 알림(게임종료 알림)
    }

    private void noticeVote(Emitters emitters) {
        emitters.sendToAll("message", new SseResponse("message", "vote start"));
        waitTimeout(emitters, 10 * 1000); // 10초
        //투표종료 알림
        emitters.sendToAll("message", new SseResponse("message", "vote end"));
    }

    private Integer getVoteResult(List<Vote> votes) {
        if (votes.isEmpty()) { // 일단 테스트용
            return null;
        }
        Map<Integer, VoteResult> voteResultMap = new HashMap<>();
        for (Vote vote : votes) {
            if (voteResultMap.containsKey(vote.getTarget())) {
                voteResultMap.get(vote.getTarget()).vote();
            } else {
                voteResultMap.put(vote.getTarget(), new VoteResult(vote.getTarget()));
            }
        }
        List<VoteResult> voteResult = voteResultMap.values().stream().sorted((o1, o2) -> o2.getCnt() - o1.getCnt()).collect(Collectors.toList());
        if (voteResult.size() > 1 && voteResult.get(0).getCnt() == voteResult.get(1).getCnt())
            return null;
        return voteResult.get(0).getTarget();
    }

    private void waitTimeout(Emitters emitters, Integer time) {
        emitters.sendToAll("message", new SseResponse("time", time.toString()));
        try {
            Thread.sleep(time + SPARE_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void vote(Integer voter, Integer target, List<Vote> votes) {
        boolean voted = false;
        for (Vote vote : votes) {
            if (Objects.equals(vote.getVoter(), voter)) {
                voted = true;
                vote.setTarget(target);
            }
        }
        if (!voted)
            votes.add(new Vote(voter, target));
    }

    private static class VoteResult {
        private final Integer target;
        private int cnt;

        public VoteResult(Integer target) {
            this.target = target;
            this.cnt = 1;
        }

        public void vote() {
            cnt++;
        }

        public Integer getTarget() {
            return target;
        }

        public int getCnt() {
            return cnt;
        }
    }
}
