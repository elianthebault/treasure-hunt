package com.treasure_hunt.infrastructure.clientside.controller;

import com.treasure_hunt.application.domain.Puzzle;
import com.treasure_hunt.application.port.input.PuzzleUseCase;
import com.treasure_hunt.infrastructure.clientside.dto.puzzle.PuzzleRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.puzzle.PuzzleResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.PuzzleDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/puzzles")
public class PuzzleController {
    private final PuzzleDtoMapper puzzleDtoMapper;
    private final PuzzleUseCase puzzleUseCase;

    public PuzzleController(PuzzleDtoMapper puzzleDtoMapper, PuzzleUseCase puzzleUseCase) {
        this.puzzleDtoMapper = puzzleDtoMapper;
        this.puzzleUseCase = puzzleUseCase;
    }

    @PostMapping
    public ResponseEntity<PuzzleResponseDto> save(
            @RequestBody PuzzleRequestDto puzzleRequestDto
    ) {
        Puzzle puzzle = puzzleDtoMapper.toPuzzle(puzzleRequestDto);
        Puzzle persisted = puzzleUseCase.save(puzzle);
        PuzzleResponseDto puzzleResponseDto = puzzleDtoMapper.toPuzzleResponseDto(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(puzzleResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PuzzleResponseDto> findById(
            @PathVariable("id") int id
    ) {
        Puzzle puzzle = puzzleUseCase.findById(id);
        PuzzleResponseDto puzzleResponseDto = puzzleDtoMapper.toPuzzleResponseDto(puzzle);

        return ResponseEntity.ok().body(puzzleResponseDto);
    }

    @PutMapping
    public ResponseEntity<PuzzleResponseDto> update(
            @PathVariable("id") int id,
            @RequestBody PuzzleRequestDto puzzleRequestDto
    ) {
        Puzzle puzzle = puzzleDtoMapper.toPuzzle(puzzleRequestDto);
        Puzzle updatedPuzzle = puzzleUseCase.update(id, puzzle);
        PuzzleResponseDto puzzleResponseDto = puzzleDtoMapper.toPuzzleResponseDto(updatedPuzzle);

        return ResponseEntity.ok(puzzleResponseDto);
    }
}
