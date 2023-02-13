package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.sse.Vote;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@ToString
@Embeddable
public class Game {
    private List<Vote> votes;
    private Integer startPerson;
    private Boolean isTurned;
    private Integer curSpeaking;
    private Long liar;
    private List<Long> participants;

    public Game(List<Long> participants) {
        this.participants = participants;
        startPerson = (int) (Math.random() * participants.size()) + 1;
        curSpeaking = startPerson;
        liar = participants.get((int) (Math.random() * participants.size()) + 1);
        votes = new CopyOnWriteArrayList<>();
    }

    public Game() {
    }

    public void vote(Integer voter, Integer target) {
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

    public Integer changeSpeaker() {
        curSpeaking = curSpeaking % participants.size() + 1;
        if (curSpeaking.equals(startPerson)) {
            if (isTurned)
                return curSpeaking;
            isTurned = true;
        }
        return null;
    }
}
