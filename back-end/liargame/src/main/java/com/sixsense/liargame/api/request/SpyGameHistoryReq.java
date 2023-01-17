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
public class SpyGameHistoryReq extends NormalGameHistoryReq {
    private Long spy;

    public SpyGameHistoryReq(Long liar, String winner, List<GameUserDto> users, Long spy) {
        super(liar, winner, users);
        this.spy = spy;
    }
}
