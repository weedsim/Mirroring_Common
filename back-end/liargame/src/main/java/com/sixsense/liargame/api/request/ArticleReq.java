package com.sixsense.liargame.api.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ArticleReq {
    private String title;
    private String content;
    private Boolean isNotice;
}
