package com.sixsense.liargame.api.sse;

import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.api.response.VoteResp;
import com.sixsense.liargame.common.model.UserInfo;
import com.sixsense.liargame.db.entity.Vote;
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
    protected String subject;
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
        if (vote.getTarget() != null)
            return new VoteResp(participants[vote.getVoter()].getName(), participants[vote.getTarget()].getName(), Objects.equals(vote.getTarget(), liar));
        else
            return new VoteResp(participants[vote.getVoter()].getName(), null, false);
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
