package com.gantuangcoc98.ebingo.repository;

import org.springframework.stereotype.Repository;

import com.gantuangcoc98.ebingo.models.Player;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    boolean existsByPlayerToken(String playerToken);
    Optional<Player> findByPlayerToken(String playerToken);
}
