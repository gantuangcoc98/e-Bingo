package com.gantuangcoc98.ebingo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.gantuangcoc98.ebingo.models.GameHost;
import com.gantuangcoc98.ebingo.models.Player;
import com.gantuangcoc98.ebingo.services.BingoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "game")
public class BingoController {

    @Autowired
    private BingoService bingoService;

    @PostMapping(path = "create")
    public GameHost createGame(@RequestBody GameHost gamehost) {
        return bingoService.createGame(gamehost.getGameCode(), gamehost.getPlayers(), gamehost.getGameCard());
    }

    @GetMapping(path = "generateCode")
    public String generateCode() {
        return bingoService.generatedUniqueCode(5);
    }

    @GetMapping(path = "getPlayers")
    public List<Player> getPlayers(@RequestParam String gameCode) {
        return bingoService.getPlayers(gameCode);
    }

    @GetMapping(path ="find")
    public String findGame(@RequestParam("gameCode") String gameCode) {
        return bingoService.findGame(gameCode);
    }

    @PostMapping(path = "addPlayer")
    public Player addPlayerTo(@RequestBody String gameCode, @RequestBody Player player) {
        return bingoService.addPlayerTo(gameCode, player);
    }

    @GetMapping(path = "getGame")
    public GameHost getGame(@RequestParam("code") String gameCode) {
        return bingoService.getGame(gameCode);
    }
    
}
