package com.gantuangcoc98.ebingo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gantuangcoc98.ebingo.models.GameHost;


@Repository
public interface BingoRepo extends JpaRepository<GameHost, Long>{
    boolean existsByGameCode(String gameCode);
    Optional<GameHost> findByGameCode(String gameCode);
}
