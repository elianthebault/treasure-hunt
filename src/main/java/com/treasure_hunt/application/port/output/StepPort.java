package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Step;

import java.util.List;
import java.util.Optional;

public interface StepPort {
    Step save(Step step);
    List<Step> findByQuestId(int id);
    Optional<Step> findByPreviousStepId(int id);
    void deleteById(int id);
}
