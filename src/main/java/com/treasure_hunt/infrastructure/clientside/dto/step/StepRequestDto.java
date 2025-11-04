package com.treasure_hunt.infrastructure.clientside.dto.step;

import com.treasure_hunt.infrastructure.clientside.dto.puzzle.PuzzleRequestDto;

import java.util.UUID;

public record StepRequestDto(
        UUID questUuid,
        PuzzleRequestDto puzzle,
        Integer previousStepId,
        Double longitude,
        Double latitude,
        Double altitude) {
}
