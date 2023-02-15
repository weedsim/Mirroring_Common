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
        super.id = room.getId();
        super.participants = room.getParticipants().toArray(new UserInfo[0]);
        super.startPerson = (int) (Math.random() * super.participants.length);
        super.curSpeaker = super.startPerson;
        super.liar = (int) (Math.random() * participants.length);
        super.votes = new CopyOnWriteArrayList<>();
        spy = (int) (Math.random() * super.participants.length);
        while (super.liar == spy) {
            spy = (int) (Math.random() * super.participants.length);
        }
    }

    @Override
    public GameResultResp getResult() {
        List<VoteResp> voteResult =
                super.votes.stream()
                        .map(this::toVoteResp)
                        .collect(Collectors.toList());
        String liarName = super.participants[super.liar].getName();
        String spyName = super.participants[spy].getName();
        List<String> citizens = Arrays.stream(super.participants).map(UserInfo::getName).filter(name -> !name.equals(liarName)).collect(Collectors.toList());

        return GameResultResp.builder()
                .winner(super.winner)
                .votes(voteResult)
                .word(super.word)
                .liar(liarName)
                .spy(spyName)
                .citizens(citizens)
                .build();
    }

    public Long getSpyUserId() {
        return super.participants[spy].getUserId();
    }
}
