package com.treasure_hunt.infrastructure.serverside.repository;

import com.treasure_hunt.infrastructure.serverside.entity.PuzzleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PuzzleRepository extends JpaRepository<PuzzleEntity, Integer> {
}
