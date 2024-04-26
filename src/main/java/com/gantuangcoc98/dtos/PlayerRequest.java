package com.gantuangcoc98.dtos;

import com.gantuangcoc98.ebingo.models.Player;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerRequest {
    private Player player;
    private String gameCode;
}
