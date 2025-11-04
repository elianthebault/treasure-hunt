package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Adventure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdventurePort {
    Adventure save(Adventure adventure);
    Optional<Adventure> findById(int id);
    Optional<Adventure> findByUuid(UUID uuid);
    List<Adventure> findByAdventurerUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    boolean existsByUuid(UUID uuid);
}
