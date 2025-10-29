package com.treasure_hunt.application.service;

import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.application.port.output.QuestPort;

public class QuestService implements QuestUseCase {
    private final QuestPort questPort;

    public QuestService(QuestPort questPort) {
        this.questPort = questPort;
    }
}
