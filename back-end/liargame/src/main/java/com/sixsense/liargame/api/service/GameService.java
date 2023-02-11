package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.AnswerReq;
import com.sixsense.liargame.api.sse.Vote;
import com.sixsense.liargame.common.model.response.GameResultResp;
import org.springframework.transaction.annotation.Transactional;

public interface GameService {
    GameResultResp normalGameStart(Long userId, Integer roomId);

    GameResultResp spyGameStart(Long userId);

    void vote(Vote vote, Integer gameId);

    @Transactional
    void insertAnswer(AnswerReq answer);
}
