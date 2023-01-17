package com.sixsense.liargame.api.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GameHistoryReq {
    private Long id;
    private Long liar;
    private String winner;
}
