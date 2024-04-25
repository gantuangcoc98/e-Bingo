package com.gantuangcoc98.ebingo.repository;

import org.springframework.stereotype.Repository;

import com.gantuangcoc98.ebingo.models.Player;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PlayerRepo extends JpaRepository<Player, Long> {
    boolean existsByPlayerToken(String playerToken);
}
