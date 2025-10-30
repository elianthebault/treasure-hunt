package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.Puzzle;
import com.treasure_hunt.application.exception.PuzzleException;
import com.treasure_hunt.application.exception.PuzzleNotFoundException;
import com.treasure_hunt.application.port.input.PuzzleUseCase;
import com.treasure_hunt.application.port.output.PuzzlePort;

import java.util.Optional;

public class PuzzleService implements PuzzleUseCase {
    private static final String NOT_FOUND = "Puzzle not found.";

    private final PuzzlePort puzzlePort;

    public PuzzleService(PuzzlePort puzzlePort) {
        this.puzzlePort = puzzlePort;
    }

    @Override
    public Puzzle save(Puzzle puzzle) {
        verifyPuzzle(puzzle);

        return puzzlePort.save(puzzle);
    }

    @Override
    public Puzzle findById(int id) {
        Optional<Puzzle> optionalPuzzle = puzzlePort.findById(id);

        if (optionalPuzzle.isEmpty())
            throw new PuzzleNotFoundException(NOT_FOUND);

        return optionalPuzzle.get();
    }

    @Override
    public Puzzle update(int id, Puzzle newPuzzle) {
        Puzzle puzzleToUpdate = findById(id);

        comparePuzzle(puzzleToUpdate, newPuzzle);

        return puzzlePort.save(puzzleToUpdate);
    }

    /*
    PRIVATE METHODS
     */

    private void verifyPuzzle(Puzzle puzzle) {
        if (puzzle == null)
            throw new PuzzleException("Puzzle is null.");
        if (puzzle.getId() != 0)
            throw new PuzzleException("Id is different from 0(zero).");
        if (puzzle.getRiddle() == null || puzzle.getRiddle().isBlank())
            throw new PuzzleException("Riddle is null or blank.");
        if (puzzle.getClue() == null || puzzle.getClue().isBlank())
            throw new PuzzleException("Clue is null or blank.");
    }

    private void comparePuzzle(Puzzle puzzleToUpdate, Puzzle newPuzzle) {
        if (newPuzzle.getRiddle() != null
                && !newPuzzle.getRiddle().isBlank()
                && !newPuzzle.getRiddle().equals(puzzleToUpdate.getRiddle()))
            puzzleToUpdate.setRiddle(newPuzzle.getRiddle());
        if (newPuzzle.getClue() != null
                && !newPuzzle.getClue().isBlank()
                && !newPuzzle.getClue().equals(puzzleToUpdate.getClue()))
            puzzleToUpdate.setClue(newPuzzle.getClue());
    }
}
