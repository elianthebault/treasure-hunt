package com.treasure_hunt.infrastructure.clientside.dto.quest;

import com.treasure_hunt.infrastructure.clientside.dto.step.StepRequestDto;

import java.util.UUID;

public record QuestRequestDto(
    UUID authorUuid,
    String name,
    String lore,
    StepRequestDto firstStep
) {
}
