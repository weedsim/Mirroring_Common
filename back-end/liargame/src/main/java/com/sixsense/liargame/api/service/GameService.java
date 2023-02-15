package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.AnswerReq;
import com.sixsense.liargame.api.response.GameResultResp;
import com.sixsense.liargame.db.entity.Vote;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    GameResultResp gameStart(Long userId, Integer roomId);

    GameResultResp spyGameStart(Long userId);

    void vote(Vote vote, Integer gameId);

    @Transactional
    void insertAnswer(AnswerReq answer);
}
