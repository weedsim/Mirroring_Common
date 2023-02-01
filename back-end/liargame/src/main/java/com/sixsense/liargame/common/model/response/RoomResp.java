package com.sixsense.liargame.common.model.response;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoomResp {
    private Long id;
    private String title;
    private Integer maxCount;
    private Integer curCount;
    private Boolean isPlaying;
    private String mode;
    private Boolean isPrivate;

    @Builder
    public RoomResp(Long id, String title, Integer maxCount, Integer curCount, Boolean isPlaying, String mode, Boolean isPrivate) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.curCount = curCount;
        this.isPlaying = isPlaying;
        this.mode = mode;
        this.isPrivate = isPrivate;
    }
}
