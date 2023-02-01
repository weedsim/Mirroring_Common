package com.sixsense.liargame.api.controller;

import com.sixsense.liargame.api.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms/{roomId}/games")
public class GameController {
    private final GameService gameService;

}
