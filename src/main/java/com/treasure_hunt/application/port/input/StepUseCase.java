package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Step;

import java.util.List;

public interface StepUseCase {
    Step save(Step step);
    List<Step> findByQuestId(int id);
    Step findByPreviousStepId(int id);
    void deleteById(int id);
    Step upgrade(int id, Step step);
}
