package com.sixsense.liargame.common.model.response;

import com.sixsense.liargame.api.sse.UserInfo;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class RoomDetail {
    private Integer roomId;
    private String title;
    private Integer maxCount;
    private Integer timeout;
    private Boolean isPlaying;
    private String mode;
    private String password;
    private Long master;
    private List<UserInfo> participants;

    @Builder
    public RoomDetail(Integer roomId, String title, Integer maxCount, Integer timeout, Boolean isPlaying, String mode, String password, Long master, List<UserInfo> participants) {
        this.roomId = roomId;
        this.title = title;
        this.maxCount = maxCount;
        this.timeout = timeout;
        this.isPlaying = isPlaying;
        this.mode = mode;
        this.password = password;
        this.master = master;
        this.participants = participants;
    }
}
