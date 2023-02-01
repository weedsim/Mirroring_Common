package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.common.model.CustomEmitter;
import com.sixsense.liargame.common.model.UserInfo;
import com.sixsense.liargame.common.model.request.SettingDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@RedisHash(value = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;
    private String title;
    private Integer maxCount;
    private Integer timeout;
    @Setter
    private Boolean isPlaying;
    private String mode;
    private String password;
    private Long master;
    @Setter
    private String answer;
    @Transient
    @Setter
    private Emitters emitters;
    private List<UserInfo> participants;
    private Long gameId;

    @Builder
    public Room(Long id, String title, Integer maxCount, Integer timeout, String mode, String password, Long master) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.timeout = timeout;
        this.mode = mode;
        this.password = password;
        this.master = master;
        this.emitters = new Emitters();
        this.participants = new ArrayList<>();
    }

    public Integer getCurCount() {
        return emitters.size();
    }

    public void changeSetting(SettingDto setting) {
        this.title = setting.getTitle();
        this.mode = setting.getMode();
        this.timeout = setting.getTimeout();
        this.password = setting.getPassword();
        this.maxCount = setting.getMaxCount();
    }

    public void enter(Long userId, String name) {
        participants.add(new UserInfo(userId, name));
        emitters.add(new CustomEmitter(userId, 1000 * 60 * 30L, name));
    }

    public void exit(Long userId) {
        emitters.remove(userId);
        for (UserInfo user : participants) {
            if (user.getUserId().equals(userId)) {
                participants.remove(user);
                return;
            }
        }
    }

    public void start() {
        isPlaying = true;
    }

    public void end() {
        isPlaying = false;
    }

}
