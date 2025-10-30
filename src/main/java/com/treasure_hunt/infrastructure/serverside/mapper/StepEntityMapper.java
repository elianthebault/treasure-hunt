package com.treasure_hunt.infrastructure.serverside.mapper;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.infrastructure.serverside.entity.StepEntity;

public interface StepEntityMapper {
    Step toStep(StepEntity stepEntity);
    StepEntity toStepEntity(Step step);
}
