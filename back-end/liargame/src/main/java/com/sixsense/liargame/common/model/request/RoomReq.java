package com.sixsense.liargame.common.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomReq {
    private String title;
    private Integer maxCount;
    private String mode;
    private String password;
    private Integer timeout;
}
