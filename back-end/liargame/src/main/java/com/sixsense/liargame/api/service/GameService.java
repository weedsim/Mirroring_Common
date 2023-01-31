package com.sixsense.liargame.api.service;

import com.sixsense.liargame.common.model.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;

public interface GameService {
    GameResultResp normalGameStart(Long userId);

    GameResultResp spyGameStart(Long userId);

    Vote vote(Vote vote);

    void insertAnswer(String answer);

}
