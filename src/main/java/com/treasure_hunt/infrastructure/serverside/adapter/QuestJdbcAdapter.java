package com.treasure_hunt.infrastructure.serverside.adapter;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.application.port.output.QuestPort;
import com.treasure_hunt.infrastructure.serverside.mapper.QuestEntityMapper;
import com.treasure_hunt.infrastructure.serverside.repository.QuestRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class QuestJdbcAdapter implements QuestPort {
    private final QuestRepository questRepository;
    private final QuestEntityMapper questEntityMapper;

    public QuestJdbcAdapter(QuestRepository questRepository, QuestEntityMapper questEntityMapper) {
        this.questRepository = questRepository;
        this.questEntityMapper = questEntityMapper;
    }

    @Override
    public Quest save(Quest quest) {
        return null;
    }

    @Override
    public List<Quest> findAll() {
        return List.of();
    }

    @Override
    public Optional<Quest> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<Quest> findByUuid(UUID uuid) {
        return Optional.empty();
    }

    @Override
    public List<Quest> findByAuthorId(int id) {
        return List.of();
    }

    @Override
    public List<Quest> findByAuthorUuid(UUID uuid) {
        return List.of();
    }

    @Override
    public boolean existsById(int id) {
        return false;
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return false;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void deleteByUuid(UUID uuid) {

    }

    @Override
    public List<Quest> findByNameContainsIgnoreCaseAndLoreContainsIgnoreCase(String search) {
        return List.of();
    }
}
