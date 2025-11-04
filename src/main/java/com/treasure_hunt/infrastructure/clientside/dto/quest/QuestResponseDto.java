package com.treasure_hunt.infrastructure.clientside.dto.quest;

import com.treasure_hunt.infrastructure.clientside.dto.step.StepResponseDto;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserResponseDto;

import java.util.UUID;

public record QuestResponseDto(
        int id,
        UUID uuid,
        UserResponseDto author,
        String name,
        String lore,
        StepResponseDto firstStep,
        Boolean valid
) {
}
