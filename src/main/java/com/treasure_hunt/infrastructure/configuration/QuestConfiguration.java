package com.treasure_hunt.infrastructure.configuration;

import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.application.port.output.QuestPort;
import com.treasure_hunt.application.service.QuestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestConfiguration {
    @Bean
    public QuestUseCase qetQuestUseCase(QuestPort questPort) {
        return new QuestService(questPort);
    }
}
