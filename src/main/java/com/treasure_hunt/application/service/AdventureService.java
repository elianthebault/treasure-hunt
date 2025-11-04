package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.Adventure;
import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.exception.AdventureException;
import com.treasure_hunt.application.exception.AdventureNotFoundException;
import com.treasure_hunt.application.port.input.AdventureUseCase;
import com.treasure_hunt.application.port.input.StepUseCase;
import com.treasure_hunt.application.port.output.AdventurePort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AdventureService implements AdventureUseCase {
    private static final String NOT_FOUND = "Adventure not found.";

    private final AdventurePort adventurePort;
    private final StepUseCase stepUseCase;

    public AdventureService(AdventurePort adventurePort, StepUseCase stepUseCase) {
        this.adventurePort = adventurePort;
        this.stepUseCase = stepUseCase;
    }

    @Override
    public Adventure save(Adventure adventure) {
        verifyAdventure(adventure);

        adventure.setUuid(UUID.randomUUID());
        //adventure.setCurrentStep(adventure.getQuest().getFirstStep());

        return adventurePort.save(adventure);
    }

    @Override
    public Adventure findById(int id) {
        Optional<Adventure> optionalAdventure = adventurePort.findById(id);

        if (optionalAdventure.isEmpty())
            throw new AdventureNotFoundException(NOT_FOUND);

        return optionalAdventure.get();
    }

    @Override
    public Adventure findByUuid(UUID uuid) {
        Optional<Adventure> optionalAdventure = adventurePort.findByUuid(uuid);

        if (optionalAdventure.isEmpty())
            throw new AdventureNotFoundException(NOT_FOUND);

        return optionalAdventure.get();
    }

    @Override
    public List<Adventure> findByAdventurerUuid(UUID uuid) {
        return adventurePort.findByAdventurerUuid(uuid);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        if (!adventurePort.existsByUuid(uuid))
            throw new AdventureNotFoundException(NOT_FOUND);

        //TODO
        //stepPort.deleteByAdventureUuid(uuid);

        adventurePort.deleteByUuid(uuid);
    }

    @Override
    public Adventure update(UUID uuid, int stepId) {
        Adventure adventureToUpdate = findByUuid(uuid);

        Step nextStep = stepUseCase.findById(stepId);

        compareAdventure(adventureToUpdate, nextStep);

        return adventurePort.save(adventureToUpdate);
    }

    /*
    PRIVATE METHODS
     */

    private void verifyAdventure(Adventure adventure) {
        if (adventure == null)
            throw new AdventureException("Adventure is null.");
        if (adventure.getId() != 0)
            throw new AdventureException("Id is different from 0(zero).");
        if (adventure.getAdventurer() == null)
            throw new AdventureException("Adventurer is null.");
        if (adventure.getQuest() == null)
            throw new AdventureException("Quest is null.");
        if (adventure.getCurrentStep() == null)
            throw new AdventureException("Current step is null.");
    }

    private void compareAdventure(Adventure adventureToUpdate, Step nextStep) {
        if (nextStep != null
                && nextStep.getId() != adventureToUpdate.getCurrentStep().getId())
            adventureToUpdate.setCurrentStep(nextStep);
    }
}
