package com.sixsense.liargame.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class GameResultResp {
    private String winner; // LIAR, CITIZEN
    private List<VoteResp> votes;
    private String word;
    private String liar;
    private Integer liarIdx;
    private Integer spyIdx;
    @Setter
    private String spy;
    private List<String> citizens;

    @Builder
    public GameResultResp(String winner, List<VoteResp> votes, String word, String liar, List<String> citizens, String spy, Integer liarIdx, Integer spyIdx) {
        this.winner = winner;
        this.votes = votes;
        this.word = word;
        this.liar = liar;
        this.spy = spy;
        this.citizens = citizens;
        this.liarIdx = liarIdx;
        this.spyIdx = spyIdx;
    }
}
