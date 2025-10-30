package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.Puzzle;
import com.treasure_hunt.infrastructure.clientside.dto.puzzle.PuzzleRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.puzzle.PuzzleResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PuzzleDtoMapper {
    Puzzle toPuzzle(PuzzleRequestDto puzzleRequestDto);
    PuzzleResponseDto toPuzzleResponseDto(Puzzle puzzle);
}
