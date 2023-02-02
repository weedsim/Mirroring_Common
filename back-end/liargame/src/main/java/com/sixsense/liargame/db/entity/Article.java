package com.sixsense.liargame.db.entity;

//import com.sixsense.liargame.db.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@ToString
public class Article extends CommunityBaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;
    private String title;
    private String content;
    private Boolean isNotice;
    private Long userId;
    private String userName;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private Integer viewCnt;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;
//    @Builder
//    public Article(Long id, String title, String content, Boolean isNotice, Long userId, String userName, Integer viewCnt, User user) {
//
//    }
    @Builder
    public Article(Long id, String title, String content, Boolean isNotice, Long userId, String userName, Integer viewCnt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
        this.userId = userId;
        this.userName = userName;
        this.viewCnt = viewCnt;
    }

    public void updateArticle(String title, String content, Boolean isNotice){
        this.title = title;
        this.content = content;
        this.isNotice = isNotice;
    }
}
