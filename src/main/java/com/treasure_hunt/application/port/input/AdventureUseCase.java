package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Adventure;

import java.util.List;
import java.util.UUID;

public interface AdventureUseCase {
    Adventure save(Adventure adventure);
    Adventure findById(int id);
    Adventure findByUuid(UUID uuid);
    List<Adventure> findByAdventurerUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    Adventure update(UUID uuid, int stepId);
}
