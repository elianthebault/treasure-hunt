package com.treasure_hunt.infrastructure.clientside.dto.adventure;

import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestResponseDto;
import com.treasure_hunt.infrastructure.clientside.dto.step.StepResponseDto;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserResponseDto;

import java.util.UUID;

public record AdventureResponseDto(
        int id,
        UUID uuid,
        UserResponseDto adventurer,
        QuestResponseDto quest,
        StepResponseDto currentStep
) {
}
