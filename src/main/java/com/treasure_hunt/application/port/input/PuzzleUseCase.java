package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.Puzzle;

public interface PuzzleUseCase {
    Puzzle save(Puzzle puzzle);
    Puzzle findById(int id);
    Puzzle update(int id, Puzzle newPuzzle);
}
