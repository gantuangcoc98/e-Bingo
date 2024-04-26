package com.gantuangcoc98.ebingo.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tblPlayer")
public class Player {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "player_token")
    private String playerToken;

    @Column(name = "player_card")
    private String playerCard;

    @Column(name = "status")
    private int status;

    public Player(String playerToken, Map<String, List<String>> playerCard, int status) {
        this.playerToken = playerToken;
        setPlayerCard(playerCard);
        this.status = status;
    }

    public String setPlayerCard(Map<String, List<String>> playerCard) {
        try {
            ObjectMapper om = new ObjectMapper();
            this.playerCard = om.writeValueAsString(playerCard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<String>> getPlayerCard() {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.readValue(this.playerCard, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
