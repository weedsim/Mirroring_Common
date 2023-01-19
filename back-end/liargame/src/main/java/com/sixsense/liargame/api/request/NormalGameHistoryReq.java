package com.sixsense.liargame.api.request;

import com.sixsense.liargame.common.model.request.GameUserReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NormalGameHistoryReq {
    private Long liar;
    private String winner;
    private List<GameUserReq> users;

    public NormalGameHistoryReq(Long liar, String winner, List<GameUserReq> users) {
        this.liar = liar;
        this.winner = winner;
        this.users = users;
    }
}
