package com.treasure_hunt.infrastructure.clientside.dto.step;

import java.util.UUID;

public record StepResponseDto(
        int id,
        UUID questUuid,
        PuzzleResponseDto puzzle,
        Integer stepNumber,
        StepResponseDto previousStep,
        Double longitude,
        Double latitude,
        Double altitude,
        Boolean valid
) {
}
