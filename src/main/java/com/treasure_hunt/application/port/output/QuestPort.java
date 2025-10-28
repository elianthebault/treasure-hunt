package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Quest;

import java.util.List;
import java.util.Optional;

public interface QuestPort {
    Quest save(Quest quest);
    List<Quest> findAll();
    Optional<Quest> findById(int id);
    List<Quest> findByUserId(int id);
    void deleteById(int id);
    List<Quest> findByNameAndLore(String search);
}
