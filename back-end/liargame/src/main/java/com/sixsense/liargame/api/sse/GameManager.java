package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.db.entity.Room;
import com.sixsense.liargame.db.repository.NormalGameRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GameManager {
    private final NormalGameRepository normalGameRepository;

    public GameManager(NormalGameRepository normalGameRepository) {
        this.normalGameRepository = normalGameRepository;
    }

    public NormalGame start(Room room) {
        NormalGame normalGame = new NormalGame((room.getParticipants().size()));
        normalGameRepository.save(normalGame);
        Emitters emitters = room.getEmitters();
        int timeout = room.getTimeout();

        room.getEmitters().setLiar(normalGame.getLiarUserId());
        //시민들에게 단어 알려주고 게임 시작 알리기
        emitters.sendToCitizens("word", normalGame.getWord());
        emitters.sendToLiar("word", "liar");
        emitters.sendToAll("msg", "game start");
        //timeout 만큼 쉼
        waitTimeout(timeout);
        //차례대로 발언
        Integer curSpeaker = normalGame.getCurSpeaker();
        while (curSpeaker != null) {
            emitters.sendToAll("curSpeaking", curSpeaker.toString());
            waitTimeout(timeout);
            curSpeaker = normalGame.changeSpeaker();
        }
        //투표시간 알림
        noticeVote(emitters);
        //투표 집계 후 투표 결과 알림 동표일경우 5번까지 투표 진행
        List<Vote> votes = normalGame.getVotes();
        Integer result = getVoteResult(votes);
        int voteCount = 1;
        while (result == null || voteCount++ < 5) {
            noticeVote(emitters);
            result = getVoteResult(votes);
        }
        //시민인 경우 바로 게임종료, 라이어인 경우 정답 입력 시간 알림
        String winner = "LIAR";
        if (Objects.equals(normalGame.getLiar(), result)) {
            emitters.sendToCitizens("msg", "selected liar");
            emitters.sendToLiar("msg", "write answer");
            waitTimeout(1000 * 10); // 정답 입력시간 10초
            if (!normalGame.getAnswer().equals(normalGame.getWord()))
                winner = "CITIZEN";
        }
        normalGame.setWinner(winner);
        return normalGame;
        //정답여부와 승패 알림(게임종료 알림)
    }

    private void noticeVote(Emitters emitters) {
        emitters.sendToAll("msg", "vote start");
        waitTimeout(30 * 1000); // 30초
        //투표종료 알림
        emitters.sendToAll("msg", "vote end");
    }

    private Integer getVoteResult(List<Vote> votes) {
        Map<Integer, VoteResult> voteResultMap = new HashMap<>();
        votes.forEach(vote -> {
            voteResultMap.computeIfPresent(vote.getTarget(), (aLong, voteResult) -> voteResult.vote());
        });
        List<VoteResult> voteResult = voteResultMap.values().stream().sorted((o1, o2) -> o2.getCnt() - o1.getCnt()).collect(Collectors.toList());
        if (voteResult.get(0).getCnt() == voteResult.get(1).getCnt())
            return null;
        return voteResult.get(0).getTarget();
    }

    private void waitTimeout(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Transactional
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
        }

        public VoteResult vote() {
            cnt++;
            return this;
        }

        public Integer getTarget() {
            return target;
        }

        public int getCnt() {
            return cnt;
        }
    }
}
