package com.treasure_hunt.infrastructure.clientside.dto.user;

import java.util.UUID;

public record UserResponseDto(
        int id,
        UUID uuid,
        String phoneNumber,
        String email,
        String firstname,
        String lastname,
        String nickname,
        String profile
) {
}
