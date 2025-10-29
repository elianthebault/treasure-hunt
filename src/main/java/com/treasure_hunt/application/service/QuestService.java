package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.exception.QuestException;
import com.treasure_hunt.application.exception.QuestNotFoundException;
import com.treasure_hunt.application.exception.UserNotFoundException;
import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.application.port.output.QuestPort;
import com.treasure_hunt.application.port.output.UserPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class QuestService implements QuestUseCase {
    private static final String NOT_FOUND = "Quest not found.";

    private final QuestPort questPort;
    private final UserPort userPort;

    public QuestService(QuestPort questPort, UserPort userPort) {
        this.questPort = questPort;
        this.userPort = userPort;
    }

    @Override
    public Quest save(Quest quest, UUID authorUuid) {
        verifyQuest(quest);

        User author = userPort.findByUuid(authorUuid)
                .orElseThrow(() -> new UserNotFoundException("Author not found."));
        quest.setAuthor(author);
        quest.setUuid(UUID.randomUUID());

        return questPort.save(quest);
    }

    @Override
    public List<Quest> findAll() {
        return questPort.findAll();
    }

    @Override
    public Quest findById(int id) {
        Optional<Quest> optionalQuest = questPort.findById(id);

        if (optionalQuest.isEmpty())
            throw new QuestNotFoundException(NOT_FOUND);

        return optionalQuest.get();
    }

    @Override
    public Quest findByUuid(UUID uuid) {
        Optional<Quest> optionalQuest = questPort.findByUuid(uuid);

        if (optionalQuest.isEmpty())
            throw new QuestNotFoundException(NOT_FOUND);

        return optionalQuest.get();
    }

    @Override
    public List<Quest> findByAuthorId(int id) {
        return questPort.findByAuthorId(id);
    }

    @Override
    public List<Quest> findByAuthorUuid(UUID uuid) {
        return questPort.findByAuthorUuid(uuid);
    }

    @Override
    public List<Quest> findByNameAndLore(String search) {
        return questPort.findByNameContainsIgnoreCaseAndLoreContainsIgnoreCase(search);
    }

    @Override
    public void deleteById(int id) {
        if (!questPort.existsById(id))
            throw new QuestNotFoundException(NOT_FOUND);

        questPort.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        if (!questPort.existsByUuid(uuid))
            throw new QuestNotFoundException(NOT_FOUND);

        questPort.deleteByUuid(uuid);
    }

    @Override
    public Quest update(UUID uuid, Quest newQuest) {
        Quest questToUpdate = findByUuid(uuid);

        compareQuest(questToUpdate, newQuest);

        return questPort.save(questToUpdate);
    }

    @Override
    public void invalid(UUID uuid) {
        Quest quest = findByUuid(uuid);

        quest.setValid(false);

        questPort.save(quest);
    }

    /*
    PRIVATE METHODS
     */

    private void verifyQuest(Quest quest) {
        if (quest == null)
            throw new QuestException("Quest is null.");
        if (quest.getId() != 0)
            throw new QuestException("Id is different from 0(zero).");
        if (quest.getAuthor() == null)
            throw new QuestException("Author is null.");
        //TODO
        //Check if author is valid
        if (quest.getName() == null || quest.getName().isBlank())
            throw new QuestException("Name is null or blank.");
        if (quest.getLore() == null || quest.getLore().isBlank())
            throw new QuestException("Lore is null or blank.");
        if (quest.getFirstStep() == null)
            throw new QuestException("First step is null.");
    }

    private void compareQuest(Quest questToUpdate, Quest newQuest) {
        if (newQuest.getName() != null
                && !newQuest.getName().isBlank()
                && !newQuest.getName().equals(questToUpdate.getName()))
            questToUpdate.setName(newQuest.getName());
        if (newQuest.getLore() != null
                && !newQuest.getLore().isBlank()
                && !newQuest.getLore().equals(questToUpdate.getLore()))
            questToUpdate.setLore(newQuest.getLore());
    }
}
