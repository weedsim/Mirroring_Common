package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.common.model.CustomEmitter;
import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.request.SettingDto;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.common.model.response.VoteResp;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@RedisHash(value = "room")
public class Room {
    @Transient
    Map<Long, String> userInfos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;
    private String title;
    private Integer maxCount;
    @Setter
    private String word;
    private Integer timeout;
    @Setter
    private Boolean isPlaying;
    private String mode;
    private String password;
    private Long master;
    @Setter
    private String answer;
    @Transient
    @Setter
    private Emitters emitters;
    @Embedded
    private Game game;

    @Builder
    public Room(Integer id, String title, Integer maxCount, Integer timeout, String mode, String password, Long master) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.timeout = timeout;
        this.mode = mode;
        this.password = password;
        this.master = master;
        this.emitters = new Emitters();
        this.userInfos = new HashMap<>();
    }

    public Integer getCurCount() {
        return emitters.size();
    }

    public void changeSetting(SettingDto setting) {
        this.title = setting.getTitle();
        this.mode = setting.getMode();
        this.timeout = setting.getTimeout();
        this.password = setting.getPassword();
        this.maxCount = setting.getMaxCount();
    }

    public void enter(Long userId, String name) {
        userInfos.put(userId, name);
        emitters.add(new CustomEmitter(userId, 1000 * 60 * 30L, name));
    }

    public void exit(Long userId) {
        emitters.remove(userId);
    }

    public GameResultResp start() {
        isPlaying = true;
        game = new Game(emitters.getParticipants());
        emitters.setLiar(game.getLiar());
        //시민들에게 단어 알려주고 게임 시작 알리기
        emitters.sendToCitizens("word", word);
        emitters.sendToLiar("word", "liar");
        emitters.sendToAll("msg", "game start");
        //timeout 만큼 쉼
        waitTimeout(timeout);
        //차례대로 발언
        Integer curSpeaker = game.getCurSpeaking();
        while (curSpeaker != null) {
            emitters.sendToAll("curSpeaking", curSpeaker.toString());
            waitTimeout(timeout);
            curSpeaker = game.changeSpeaker();
        }
        //투표시간 알림
        noticeVote();
        //투표 집계 후 투표 결과 알림 동표일경우 5번까지 투표 진행
        Long result = getResult(game.getVotes());
        int voteCount = 1;
        while (result == null || voteCount++ < 5) {
            noticeVote();
            result = getResult(game.getVotes());
        }
        String winner = "LIAR";
        //시민인 경우 바로 게임종료, 라이어인 경우 정답 입력 시간 알림
        if (Objects.equals(game.getLiar(), result)) {
            emitters.sendToCitizens("msg", "selected liar");
            emitters.sendToLiar("msg", "write answer");
            waitTimeout(1000 * 10); // 정답 입력시간 10초
            if (!answer.equals(word))
                winner = "CITIZEN";
        }
        //정답여부와 승패 알림(게임종료 알림)
        List<VoteResp> votes = game.getVotes().stream().map(this::toVoteResp).collect(Collectors.toList());
        String liarName = userInfos.get(game.getLiar());
        List<String> citizens = userInfos.values().stream().filter(name -> !name.equals(liarName)).collect(Collectors.toList());
        end();
        return GameResultResp.builder()
                .winner(winner)
                .votes(votes)
                .word(word)
                .liar(liarName)
                .citizens(citizens)
                .build();
    }

    private VoteResp toVoteResp(Vote vote) {
        return new VoteResp(userInfos.get(vote.getVoter()), userInfos.get(vote.getTarget()), Objects.equals(vote.getTarget(), game.getLiar()));
    }

    private void noticeVote() {
        emitters.sendToAll("msg", "vote start");
        waitTimeout(30 * 1000); // 30초
        //투표종료 알림
        emitters.sendToAll("msg", "vote end");
    }

    private Long getResult(List<Vote> votes) {
        Map<Long, VoteResult> voteResultMap = new HashMap<>();
        votes.forEach(vote -> {
            voteResultMap.computeIfPresent(vote.getTarget(), (aLong, voteResult) -> voteResult.vote());
        });
        List<VoteResult> voteResult = voteResultMap.values().stream().sorted((o1, o2) -> o2.getCnt() - o1.getCnt()).collect(Collectors.toList());
        if (voteResult.get(0).getCnt() == voteResult.get(1).getCnt())
            return null;
        return voteResult.get(0).getTarget();
    }

    public void end() {
        isPlaying = false;
        word = null;
        game = null;
    }

    private void waitTimeout(Integer time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            Thread.interrupted();
        }
    }

    private static class VoteResult {
        private Long target;
        private int cnt;

        public VoteResult(Long target) {
            this.target = target;
            cnt = 1;
        }

        public VoteResult vote() {
            cnt++;
            return this;
        }

        public Long getTarget() {
            return target;
        }

        public int getCnt() {
            return cnt;
        }
    }

}
