package com.sixsense.liargame.common.model.response;

public class VoteResp {
    private String voter;
    private String target;
    private Boolean isCorrect;

    public VoteResp(String voter, String target, Boolean isCorrect) {
        this.voter = voter;
        this.target = target;
        this.isCorrect = isCorrect;
    }
}
