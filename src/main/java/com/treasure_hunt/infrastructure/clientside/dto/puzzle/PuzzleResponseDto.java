package com.treasure_hunt.infrastructure.clientside.dto.puzzle;

public record PuzzleResponseDto(
        int id,
        String riddle,
        String clue
) {
}
