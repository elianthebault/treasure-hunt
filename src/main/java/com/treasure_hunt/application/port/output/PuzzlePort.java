package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.Puzzle;

import java.util.Optional;

public interface PuzzlePort {
    Puzzle save(Puzzle puzzle);
    Optional<Puzzle> findById(int id);
}
