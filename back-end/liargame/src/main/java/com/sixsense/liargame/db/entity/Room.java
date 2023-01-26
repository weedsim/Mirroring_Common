package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.common.model.request.SettingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@Getter
@RedisHash(value = "room")
@NoArgsConstructor
public class Room {
    @Id
    private Integer id;
    private String title;
    private Integer maxCount;
    private Integer curCount;
    private Boolean isPlaying;
    private String mode;
    private String password;

    @Builder
    public Room(Integer id, String title, Integer maxCount, Integer curCount, Boolean isPlaying, String mode, String password) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.curCount = curCount;
        this.isPlaying = isPlaying;
        this.mode = mode;
        this.password = password;
    }

    public void changeSetting(SettingDto setting) {
        this.title = setting.getTitle();
        this.mode = setting.getMode();
        this.password = setting.getPassword();
        this.maxCount = setting.getMaxCount();
    }

    public void enter() {
        curCount++;
    }

    public void exit() {
        curCount--;
    }

    public void start() {
        isPlaying = true;
    }

    public void end() {
        isPlaying = false;
    }
}
