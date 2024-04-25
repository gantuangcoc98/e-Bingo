package com.gantuangcoc98.ebingo.services;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gantuangcoc98.ebingo.repository.BingoRepo;
import com.gantuangcoc98.ebingo.repository.PlayerRepo;

@Service
public class PlayerService {
    
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, BingoRepo bingoRepo) {
        this.playerRepo = playerRepo;
    }

    public String generatedUniquePlayerToken(int length) {
        String generatedCode;

        do {
            generatedCode = generateCode(length);
        } while (playerRepo.existsByPlayerToken(generatedCode));

        return generatedCode;
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
}
