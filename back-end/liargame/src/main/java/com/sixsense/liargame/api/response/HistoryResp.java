package com.sixsense.liargame.api.response;

import com.sixsense.liargame.common.model.response.GameUserDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryResp {
    private String result;
    private String role;
    private LocalDateTime playedTime;
    private List<GameUserDto> users;
}
