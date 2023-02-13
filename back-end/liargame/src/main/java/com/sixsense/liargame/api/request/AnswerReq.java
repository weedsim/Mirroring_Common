package com.sixsense.liargame.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerReq {
    private String answer;
    private Integer roomId;
}
