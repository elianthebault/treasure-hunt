package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Step;

import java.util.List;
import java.util.UUID;

public interface StepUseCase {
    Step save(Step step);
    List<Step> findByQuestUuidOrderByStepNumberAsc(UUID uuid);
    Step findById(int id);
    Step findByPreviousStepId(int id);
    void deleteById(int id);
    Step update(int id, Step step);
    void invalid(int id);
}
