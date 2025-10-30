package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.exception.StepException;
import com.treasure_hunt.application.exception.StepNotFoundException;
import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.application.port.input.StepUseCase;
import com.treasure_hunt.application.port.output.StepPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class StepService implements StepUseCase {
    private static final String NOT_FOUND = "Step not found.";

    private final StepPort stepPort;
    private final QuestUseCase questUseCase;

    public StepService(StepPort stepPort, QuestUseCase questUseCase) {
        this.stepPort = stepPort;
        this.questUseCase = questUseCase;
    }

    @Override
    public Step save(Step step) {
        VerifyStep(step);

        step.setValid(true);

        if (step.getPreviousStep() != null) {
            step.setStepNumber(step.getPreviousStep().getStepNumber() + 1);
        } else {
            step.setStepNumber(1);
        }

        return stepPort.save(step);
    }

    @Override
    public List<Step> findByQuestUuidOrderByStepNumberAsc(UUID uuid) {
        return stepPort.findByQuestUuidOrderByStepNumberAsc(uuid);
    }

    @Override
    public Step findById(int id) {
        Optional<Step> optionalStep = stepPort.findById(id);

        if (optionalStep.isEmpty())
            throw new StepNotFoundException(NOT_FOUND);

        return optionalStep.get();
    }

    @Override
    public Step findByPreviousStepId(int id) {
        Optional<Step> optionalStep = stepPort.findByPreviousStepId(id);

        if (optionalStep.isEmpty())
            throw new StepNotFoundException(NOT_FOUND);

        return optionalStep.get();
    }

    @Override
    public void deleteById(int id) {
        if (!stepPort.existsById(id))
            throw new StepNotFoundException(NOT_FOUND);

        stepPort.deleteById(id);
    }

    @Override
    public Step update(int id, Step newStep) {
        Step stepToUpdate = findById(id);

        compareStep(stepToUpdate, newStep);

        return stepPort.save(stepToUpdate);
    }

    @Override
    public void invalid(int id) {
        Step step = findById(id);

        step.setValid(false);

        questUseCase.invalid(step.getQuestUuid());

        stepPort.save(step);
    }

    /*
    PRIVATE METHODS
     */

    private void VerifyStep(Step step) {
        if (step == null)
            throw new StepException("Step is null.");
        if (step.getId() != 0)
            throw new StepException("Id is different from 0(zero).");
        if (step.getQuestUuid() == null)
            throw new StepException("Quest UUID is null.");
        if (step.getPuzzle() == null)
            throw new StepException("Puzzle is null.");
        if (step.getLongitude() == null)
            throw new StepException("Longitude is null.");
        if (step.getLatitude() == null)
            throw new StepException("Latitude is null.");
        if (step.getAltitude() == null)
            throw new StepException("Altitude is null.");
    }

    private void compareStep(Step stepToUpdate, Step newStep) {
        if (newStep.getLongitude() != null
                && !newStep.getLongitude().equals(stepToUpdate.getLongitude()))
            stepToUpdate.setLongitude(newStep.getLongitude());
        if (newStep.getLatitude() != null
                && !newStep.getLatitude().equals(stepToUpdate.getLatitude()))
            stepToUpdate.setLatitude(newStep.getLatitude());
        if (newStep.getAltitude() != null
                && !newStep.getAltitude().equals(stepToUpdate.getAltitude()))
            stepToUpdate.setAltitude(newStep.getAltitude());
    }
}
