package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.request.NormalGameHistoryReq;
import com.sixsense.liargame.api.request.SpyGameHistoryReq;
import com.sixsense.liargame.api.response.HistoryResp;

import java.util.List;

public interface HistoryService {
    void insertNormalPlay(NormalGameHistoryReq history);

    void insertSpyPlay(SpyGameHistoryReq history);

    List<HistoryResp> selectSpyGameHistory(Long userId);

    List<HistoryResp> selectNormalGameHistory(Long userId);
}