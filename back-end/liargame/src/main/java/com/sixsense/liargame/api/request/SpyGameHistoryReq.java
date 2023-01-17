package com.sixsense.liargame.api.request;

import com.sixsense.liargame.common.model.request.GameUserDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpyGameHistoryReq extends GameHistoryReq {
    private Long spy;
    private List<GameUserDto> users;
}
