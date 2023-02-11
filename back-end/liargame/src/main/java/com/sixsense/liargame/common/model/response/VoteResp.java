package com.sixsense.liargame.common.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
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
