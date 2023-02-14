package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.common.model.response.GameResultResp;
import com.sixsense.liargame.common.model.response.VoteResp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
public abstract class Game {

    protected Integer id;
    @Setter
    protected String word;
    @Setter
    protected String answer;
    protected List<Vote> votes;
    protected int startPerson;
    protected UserInfo[] participants;
    protected boolean isTurned;
    protected Integer curSpeaker;
    protected int liar;
    @Setter
    protected String winner;

    public abstract GameResultResp getResult();

    protected VoteResp toVoteResp(Vote vote) {
        return new VoteResp(participants[vote.getVoter()].getName(), participants[vote.getTarget()].getName(), Objects.equals(vote.getTarget(), liar));
    }

    public Integer changeSpeaker() {
        curSpeaker = (curSpeaker + 1) % participants.length;
        if (curSpeaker.equals(startPerson)) {
            if (!isTurned) {
                isTurned = true;
                return curSpeaker;
            }
            return null;
        }
        return curSpeaker;
    }

    public Long getLiarUserId() {
        return participants[liar].getUserId();
    }
}
