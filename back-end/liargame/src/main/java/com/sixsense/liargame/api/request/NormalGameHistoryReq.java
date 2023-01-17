package com.sixsense.liargame.api.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NormalGameHistoryReq {
    private Long id;
    private String liar;
    private String winner;
    private String word;
}
