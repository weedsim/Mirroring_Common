package com.sixsense.liargame.common.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GameResultResp {
    String winner; // LIAR, CITIZEN
    List<VoteResp> votes;
    String word;
    String liar;
    List<String> citizens;

    @Builder
    public GameResultResp(String winner, List<VoteResp> votes, String word, String liar, List<String> citizens) {
        this.winner = winner;
        this.votes = votes;
        this.word = word;
        this.liar = liar;
        this.citizens = citizens;
    }
}
