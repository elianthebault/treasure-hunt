package com.treasure_hunt.infrastructure.serverside.mapper;

import com.treasure_hunt.application.domain.Puzzle;
import com.treasure_hunt.infrastructure.serverside.entity.PuzzleEntity;

public interface PuzzleEntityMapper {
    Puzzle toPuzzle(PuzzleEntity puzzleEntity);
    PuzzleEntity toPuzzleEntity(Puzzle puzzle);
}
