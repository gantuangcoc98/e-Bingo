package com.gantuangcoc98.ebingo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gantuangcoc98.ebingo.models.Player;
import com.gantuangcoc98.ebingo.services.PlayerService;

@RestController
@RequestMapping(path = "player")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @GetMapping(path = "generateToken")
    public String generateToken() {
        return playerService.generatedUniquePlayerToken(8);
    }

    @GetMapping(path = "getPlayer")
    public Player getPlayer(@RequestParam("token") String playerToken) {
        return playerService.getPlayer(playerToken);
    }

}
