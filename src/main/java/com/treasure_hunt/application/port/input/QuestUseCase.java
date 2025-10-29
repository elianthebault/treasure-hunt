package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Quest;

import java.util.List;
import java.util.UUID;

public interface QuestUseCase {
    Quest save(Quest quest, UUID authorUuid);
    List<Quest> findAll();
    Quest findById(int id);
    Quest findByUuid(UUID uuid);
    List<Quest> findByAuthorId(int id);
    List<Quest> findByAuthorUuid(UUID uuid);
    List<Quest> findByNameAndLore(String search);
    void deleteById(int id);
    void deleteByUuid(UUID uuid);
    Quest update(UUID uuid, Quest quest);
    void invalid(UUID uuid);
}
