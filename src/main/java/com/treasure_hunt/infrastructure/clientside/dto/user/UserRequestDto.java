package com.treasure_hunt.infrastructure.clientside.dto.user;

public record UserRequestDto(
        String phoneNumber,
        String email,
        String firstname,
        String lastname,
        String nickname,
        String password
) {
}
