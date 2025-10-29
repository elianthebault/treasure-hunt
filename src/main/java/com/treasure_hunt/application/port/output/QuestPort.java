package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Quest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestPort {
    Quest save(Quest quest);
    List<Quest> findAll();
    Optional<Quest> findById(int id);
    Optional<Quest> findByUuid(UUID uuid);
    List<Quest> findByAuthorId(int id);
    List<Quest> findByAuthorUuid(UUID uuid);
    boolean existsById(int id);
    boolean existsByUuid(UUID uuid);
    void deleteById(int id);
    void deleteByUuid(UUID uuid);
    List<Quest> findByNameContainsIgnoreCaseAndLoreContainsIgnoreCase(String search);
}
