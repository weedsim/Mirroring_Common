package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.api.response.VoteResp;
import com.sixsense.liargame.api.sse.Game;
import com.sixsense.liargame.common.model.UserInfo;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
@RedisHash("normal_game")
public class SpyGame extends Game {
    private int spy;

    public SpyGame(Room room) {
        this.id = room.getId();
        this.participants = room.getParticipants().toArray(new UserInfo[0]);
        startPerson = (int) (Math.random() * participants.length);
        curSpeaker = startPerson;
        liar = (int) (Math.random() * participants.length);
        votes = new CopyOnWriteArrayList<>();
        spy = (int) (Math.random() * super.participants.length);
        while (super.liar == spy) {
            spy = (int) (Math.random() * super.participants.length);
        }
    }

    @Override
    public GameResultResp getResult() {
        List<VoteResp> voteResult =
                votes.stream()
                        .map(this::toVoteResp)
                        .collect(Collectors.toList());
        String liarName = participants[liar].getName();
        List<String> citizens = Arrays.stream(participants).map(UserInfo::getName).filter(name -> !name.equals(liarName)).collect(Collectors.toList());

        String spyName = super.participants[spy].getName();
        return GameResultResp.builder()
                .winner(winner)
                .votes(voteResult)
                .word(word)
                .liar(liarName)
                .spy(spyName)
                .citizens(citizens)
                .build();
    }

    public Long getSpyUserId() {
        return super.participants[spy].getUserId();
    }
}
