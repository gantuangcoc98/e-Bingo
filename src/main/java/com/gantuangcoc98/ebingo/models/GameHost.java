package com.gantuangcoc98.ebingo.models;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tblGame")
public class GameHost {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(unique = true, name = "game_code")
    private String gameCode;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_game_code", referencedColumnName = "game_code")
    private List<Player> players;

    @Column(name = "game_card")
    private String gameCard;

    public GameHost(String gameCode, List<Player> players, Map<String, List<String>> gameCard) {
        this.gameCode = gameCode;
        this.players = players;
        setGameCard(gameCard);
    }

    public String setGameCard(Map<String, List<String>> gameCard) {
        try {
            ObjectMapper om = new ObjectMapper();
            this.gameCard = om.writeValueAsString(gameCard);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public Map<String, List<String>> getGameCard() {
        try {
            ObjectMapper om = new ObjectMapper();
            return om.readValue(this.gameCard, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
