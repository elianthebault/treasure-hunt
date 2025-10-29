package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Quest;

import java.util.List;

public interface QuestUseCase {
    Quest save(Quest quest);
    List<Quest> findAll();
    Quest findById(int id);
    List<Quest> findByAuthorId(int id);
    void deleteById(int id);
    Quest update(int id, Quest quest);
}
