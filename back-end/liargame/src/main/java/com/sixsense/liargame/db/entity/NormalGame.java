package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.api.response.VoteResp;
import com.sixsense.liargame.api.sse.Game;
import com.sixsense.liargame.common.model.UserInfo;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
public class NormalGame extends Game {

    public NormalGame(Room room) {
        super.id = room.getId();
        super.participants = room.getParticipants().toArray(new UserInfo[0]);
        super.startPerson = (int) (Math.random() * super.participants.length);
        super.curSpeaker = super.startPerson;
        super.liar = (int) (Math.random() * super.participants.length);
        super.votes = new CopyOnWriteArrayList<>();
    }

    public GameResultResp getResult() {
        boolean[] voted = new boolean[super.participants.length];
        for (Vote vote : super.votes) {
            voted[vote.getVoter()] = true;
        }
        for (int i = 0; i < voted.length; i++) {
            if (!voted[i]) {
                votes.add(new Vote(i, null));
            }
        }
        List<VoteResp> voteResult =
                super.votes.stream()
                        .map(this::toVoteResp)
                        .collect(Collectors.toList());
        String liarName = super.participants[super.liar].getName();
        List<String> citizens = Arrays.stream(super.participants).map(UserInfo::getName).filter(name -> !name.equals(liarName)).collect(Collectors.toList());
        return GameResultResp.builder()
                .winner(super.winner)
                .votes(voteResult)
                .word(super.word)
                .liarIdx(super.liar)
                .liar(liarName)
                .citizens(citizens)
                .build();
    }
}
