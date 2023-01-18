package com.sixsense.liargame.api.request;

import com.sixsense.liargame.common.model.request.GameUserDto;
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
    private List<GameUserDto> users;

    public NormalGameHistoryReq(Long liar, String winner, List<GameUserDto> users) {
        this.liar = liar;
        this.winner = winner;
        this.users = users;
    }
}
