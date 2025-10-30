package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Step;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StepPort {
    Step save(Step step);
    List<Step> findByQuestUuidOrderByStepNumberAsc(UUID uuid);
    Optional<Step> findById(int id);
    Optional<Step> findByPreviousStepId(int id);
    void deleteById(int id);
    boolean existsById(int id);
}
