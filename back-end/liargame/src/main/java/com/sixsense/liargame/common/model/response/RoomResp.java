package com.sixsense.liargame.common.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class RoomResp {
    private Integer id;
    private String title;
    private Integer maxCount;
    private Integer curCount;
    private Boolean isPlaying;
    private String mode;
    private Boolean isPrivate;

    @Builder
    public RoomResp(Integer id, String title, Integer maxCount, Integer curCount, Boolean isPlaying, String mode, Boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.curCount = curCount;
        this.isPlaying = isPlaying;
        this.mode = mode;
        this.isPrivate = isPrivate;
    }
}
