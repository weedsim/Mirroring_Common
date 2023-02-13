package com.sixsense.liargame.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialLoginResp {
    private String accessToken;
    private String refreshToken;
}
