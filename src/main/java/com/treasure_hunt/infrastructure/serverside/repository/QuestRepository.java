package com.treasure_hunt.infrastructure.serverside.repository;

import com.treasure_hunt.infrastructure.serverside.entity.QuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestRepository extends JpaRepository<QuestEntity, Integer> {
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    List<QuestEntity> findByNameContainsIgnoreCaseAndLoreContainsIgnoreCase(String search);
}
