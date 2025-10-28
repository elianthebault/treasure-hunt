package com.treasure_hunt.infrastructure.clientside.dto.user;

public record UserResponseDto(
        int id,
        String phoneNumber,
        String email,
        String firstname,
        String lastname,
        String nickname,
        String profile
) {
}
