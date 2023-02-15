package com.sixsense.liargame.db.entity;

import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.api.response.VoteResp;
import com.sixsense.liargame.api.sse.Game;
import com.sixsense.liargame.common.model.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
public class NormalGame extends Game {

    private Integer id;
    @Setter
    private String word;
    @Setter
    private String answer;
    private List<Vote> votes;
    private int startPerson;
    private UserInfo[] participants;
    private boolean isTurned;
    private Integer curSpeaker;
    private int liar;
    @Setter
    private String winner;

    public NormalGame(Room room) {
        this.id = room.getId();
        this.participants = room.getParticipants().toArray(new UserInfo[0]);
        startPerson = (int) (Math.random() * participants.length);
        curSpeaker = startPerson;
        liar = (int) (Math.random() * participants.length);
        votes = new CopyOnWriteArrayList<>();
    }

    public GameResultResp getResult() {
        List<VoteResp> voteResult =
                votes.stream()
                        .map(this::toVoteResp)
                        .collect(Collectors.toList());
        String liarName = participants[liar].getName();
        List<String> citizens = Arrays.stream(participants).map(UserInfo::getName).filter(name -> !name.equals(liarName)).collect(Collectors.toList());
        return GameResultResp.builder()
                .winner(winner)
                .votes(voteResult)
                .word(word)
                .liar(liarName)
                .citizens(citizens)
                .build();
    }
}
