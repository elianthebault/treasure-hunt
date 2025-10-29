package com.treasure_hunt.infrastructure.clientside.dto.quest;

import java.util.UUID;

public record QuestRequestDto(
    UUID authorUuid,
    String name,
    String lore,
    StepRequestDto firstStep
) {
}
