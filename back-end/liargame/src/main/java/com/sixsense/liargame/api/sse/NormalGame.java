package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.common.model.UserInfo;
import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.common.model.response.VoteResp;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
@RedisHash("normal_game")
public class NormalGame {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String word;
    @Setter
    private String answer;
    private List<Vote> votes;
    private int startPerson;
    private UserInfo[] participants;
    private boolean isTurned;
    private Integer curSpeaker;
    private int liar;
    @Setter
    private String winner;

    public NormalGame(int size) {
        this.participants = new UserInfo[size];
        startPerson = (int) (Math.random() * participants.length);
        curSpeaker = startPerson;
        liar = (int) (Math.random() * participants.length);
        votes = new CopyOnWriteArrayList<>();
    }

    public GameResultResp getResult() {
        List<VoteResp> voteResult =
                votes.stream()
                        .map(this::toVoteResp)
                        .collect(Collectors.toList());
        String liarName = participants[liar].getName();
        List<String> citizens = Arrays.stream(participants).map(UserInfo::getName).filter(name -> !name.equals(liarName)).collect(Collectors.toList());
        return GameResultResp.builder()
                .winner(winner)
                .votes(voteResult)
                .word(word)
                .liar(liarName)
                .citizens(citizens)
                .build();
    }

    private VoteResp toVoteResp(Vote vote) {
        return new VoteResp(participants[vote.getVoter()].getName(), participants[vote.getTarget()].getName(), Objects.equals(vote.getTarget(), liar));
    }

    public Integer changeSpeaker() {
        curSpeaker = (curSpeaker + 1) % participants.length;
        if (curSpeaker.equals(startPerson) && !isTurned) {
            isTurned = true;
            return curSpeaker;
        }
        return null;
    }

    public Long getLiarUserId() {
        return participants[liar].getUserId();
    }
}
