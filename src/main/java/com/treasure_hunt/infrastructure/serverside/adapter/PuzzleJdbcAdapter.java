package com.treasure_hunt.infrastructure.serverside.adapter;

import com.treasure_hunt.application.domain.Puzzle;
import com.treasure_hunt.application.port.output.PuzzlePort;
import com.treasure_hunt.infrastructure.serverside.entity.PuzzleEntity;
import com.treasure_hunt.infrastructure.serverside.mapper.PuzzleEntityMapper;
import com.treasure_hunt.infrastructure.serverside.repository.PuzzleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PuzzleJdbcAdapter implements PuzzlePort {
    private final PuzzleRepository puzzleRepository;
    private final PuzzleEntityMapper puzzleEntityMapper;

    public PuzzleJdbcAdapter(PuzzleRepository puzzleRepository, PuzzleEntityMapper puzzleEntityMapper) {
        this.puzzleRepository = puzzleRepository;
        this.puzzleEntityMapper = puzzleEntityMapper;
    }

    @Override
    public Puzzle save(Puzzle puzzle) {
        PuzzleEntity entity = puzzleEntityMapper.toPuzzleEntity(puzzle);
        PuzzleEntity persisted = puzzleRepository.save(entity);
        Puzzle result = puzzleEntityMapper.toPuzzle(persisted);

        return result;
    }

    @Override
    public Optional<Puzzle> findById(int id) {
        Optional<PuzzleEntity> optional = puzzleRepository.findById(id);

        return optional.map(puzzleEntityMapper::toPuzzle);
    }
}
