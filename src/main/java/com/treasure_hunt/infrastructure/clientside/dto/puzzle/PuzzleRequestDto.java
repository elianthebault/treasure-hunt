package com.treasure_hunt.infrastructure.clientside.dto.puzzle;

public record PuzzleRequestDto(
        String riddle,
        String clue
) {
}
