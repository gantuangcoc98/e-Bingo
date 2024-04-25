package com.gantuangcoc98.ebingo.services;

import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gantuangcoc98.ebingo.models.GameHost;
import com.gantuangcoc98.ebingo.models.Player;
import com.gantuangcoc98.ebingo.repository.BingoRepo;

@Service
public class BingoService {

    @Autowired
    private BingoRepo bingoRepo;

    public GameHost createGame(String gameCode, List<Player> players, Map<String,List<String>> game_card) {
        GameHost gameHost = new GameHost(gameCode, players, game_card);
        bingoRepo.save(gameHost);

        return gameHost;
    }

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = UPPER + LOWER + DIGITS;
    private static final SecureRandom RANDOM = new SecureRandom();

    private String generateCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(ALPHANUMERIC.length());
            char randomChar = ALPHANUMERIC.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public String generatedUniqueCode(int length) {
        String generatedCode;

        do {
            generatedCode = generateCode(length);
        } while (bingoRepo.existsByGameCode(generatedCode));

        return generatedCode;
    }

    public List<Player> getPlayers(String gameCode) {
        Optional<GameHost> gameHost = bingoRepo.findByGameCode(gameCode);

        if (gameHost.isPresent()) {
            GameHost _gameHost = gameHost.get();
            return _gameHost.getPlayers();
        }

        return null;
    }

    public String findGame(String gameCode) {
        Optional<GameHost> gameHost = bingoRepo.findByGameCode(gameCode);

        if (gameHost.isPresent()) {
            GameHost _gameHost = gameHost.get();
            
            return _gameHost.getGameCode();
        }

        return null;
    }

    public Player addPlayerTo(String gameCode, Player player) {
        Optional<GameHost> gameHost = bingoRepo.findByGameCode(gameCode);
        if (gameHost.isPresent()) {
            GameHost _gameHost = gameHost.get();
            _gameHost.getPlayers().add(player);

            bingoRepo.save(_gameHost);

            return player;
        }

        return null;
    }

    public GameHost getGame(String gameCode) {
        Optional<GameHost> gameHost = bingoRepo.findByGameCode(gameCode);

        if (gameHost.isPresent()) {
            return gameHost.get();
        }

        return null;
    }
    
}
