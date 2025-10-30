package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.infrastructure.clientside.dto.step.StepRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.step.StepResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StepDtoMapper {
    @Mapping(target = "previousStep", expression = "java(stepUseCase.findById(stepRequestDto.previousStepId()))")
    Step toStep(StepRequestDto stepRequestDto);

    StepResponseDto toStepResponseDto(Step step);
}
