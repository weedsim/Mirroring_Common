package com.sixsense.liargame.config;

import com.sixsense.liargame.api.service.HistoryService;
import com.sixsense.liargame.api.service.impl.HistoryServiceImpl;
import com.sixsense.liargame.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final NormalHistoryRepository normalHistoryRepository;
    private final NormalPlayRepository normalPlayRepository;
    private final SpyHistoryRepository spyHistoryRepository;
    private final SpyPlayRepository spyPlayRepository;
    private final UserRepository userRepository;

    @Bean
    public HistoryService historyService() {
        return new HistoryServiceImpl(normalHistoryRepository, normalPlayRepository, spyPlayRepository, spyHistoryRepository, userRepository);
    }
}
