package com.sixsense.liargame.api.request;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SpyGameHistoryReq extends NormalGameHistoryReq {
    private String spy;
}
