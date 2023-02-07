package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.sse.Emitters;
import com.sixsense.liargame.common.model.UserInfo;
import com.sixsense.liargame.common.model.request.SettingDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class Room {
    @Setter
    private Integer id;
    private String title;
    private Integer maxCount;
    private Integer timeout;
    @Setter
    private Boolean isPlaying;
    private String mode;
    private String password;
    @Setter
    private Long master;
    @Setter
    private String answer;
    @Setter
    private Emitters emitters;
    private List<UserInfo> participants;

    @Builder
    public Room(Integer id, String title, Integer maxCount, Integer timeout, String mode, String password, Long master) {
        this.id = id;
        this.title = title;
        this.maxCount = maxCount;
        this.timeout = timeout;
        this.mode = mode;
        this.password = password;
        this.master = master;
        this.isPlaying = false;
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
