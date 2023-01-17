package com.sixsense.liargame.api.service;

import com.sixsense.liargame.api.response.HistoryResp;

import java.util.List;

public interface HistoryService {
    List<HistoryResp> getSpyGameHistory(Long userId);

    List<HistoryResp> getNormalGameHistory(Long userId);
}
