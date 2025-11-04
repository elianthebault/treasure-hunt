package com.treasure_hunt.infrastructure.configuration;

import com.treasure_hunt.application.port.input.AdventureUseCase;
import com.treasure_hunt.application.port.input.StepUseCase;
import com.treasure_hunt.application.port.output.AdventurePort;
import com.treasure_hunt.application.service.AdventureService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdventureConfiguration {
    @Bean
    public AdventureUseCase getAdventureUseCase(AdventurePort adventurePort, StepUseCase stepUseCase) {
        return new AdventureService(adventurePort, stepUseCase);
    }
}
