package com.treasure_hunt.infrastructure.serverside.repository;

import com.treasure_hunt.infrastructure.serverside.entity.AdventureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdventureRepository extends JpaRepository<AdventureEntity, Integer> {
    Optional<AdventureEntity> findByUuid(UUID uuid);
    List<AdventureEntity> findByAdventurerUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}
