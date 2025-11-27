package com.treasure_hunt.infrastructure.clientside.dto.user;

public record LoginRequestDto(
        String email,
        String password
) {
}
