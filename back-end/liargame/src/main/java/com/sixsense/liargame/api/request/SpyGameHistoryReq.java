package com.sixsense.liargame.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SpyGameHistoryReq extends NormalGameHistoryReq {
    private Long spy;

    public SpyGameHistoryReq(Long liar, String winner, List<GameUserReq> users, Long spy) {
        super(liar, winner, users);
        this.spy = spy;
    }
}
