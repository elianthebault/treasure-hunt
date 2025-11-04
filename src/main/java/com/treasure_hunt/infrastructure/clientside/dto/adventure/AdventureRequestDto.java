package com.treasure_hunt.infrastructure.clientside.dto.adventure;

import java.util.UUID;

public record AdventureRequestDto(
        UUID adventurerUuid,
        UUID questUuid,
        int currentStepId
) {
}
