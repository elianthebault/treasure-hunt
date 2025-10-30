package com.treasure_hunt.infrastructure.configuration;

import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.application.port.input.StepUseCase;
import com.treasure_hunt.application.port.output.StepPort;
import com.treasure_hunt.application.service.StepService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfiguration {
    @Bean
    public StepUseCase getStepUseCase(StepPort stepPort, QuestUseCase questUseCase) {
        return new StepService(stepPort, questUseCase);
    }
}
