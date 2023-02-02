package com.sixsense.liargame.common.model.response;

public class VoteResp {
    String voter;
    String target;
    Boolean isCorrect;

    public VoteResp(String voter, String target, Boolean isCorrect) {
        this.voter = voter;
        this.target = target;
        this.isCorrect = isCorrect;
    }
}
