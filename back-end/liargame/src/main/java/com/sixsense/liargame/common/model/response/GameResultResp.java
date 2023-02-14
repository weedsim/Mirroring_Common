package com.sixsense.liargame.common.model.response;

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
    @Setter
    private String spy;
    private List<String> citizens;

    @Builder
    public GameResultResp(String winner, List<VoteResp> votes, String word, String liar, List<String> citizens, String spy) {
        this.winner = winner;
        this.votes = votes;
        this.word = word;
        this.liar = liar;
        this.spy = spy;
        this.citizens = citizens;
    }
}
