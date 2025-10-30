package com.treasure_hunt.infrastructure.configuration;

import com.treasure_hunt.application.port.input.PuzzleUseCase;
import com.treasure_hunt.application.port.output.PuzzlePort;
import com.treasure_hunt.application.service.PuzzleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PuzzleConfiguration {
    @Bean
    public PuzzleUseCase getPuzzleUseCase(PuzzlePort puzzlePort) {
        return new PuzzleService(puzzlePort);
    }
}
