package com.treasure_hunt.infrastructure.clientside.mapper.helper;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.exception.QuestNotFoundException;
import com.treasure_hunt.application.exception.StepNotFoundException;
import com.treasure_hunt.application.exception.UserNotFoundException;
import com.treasure_hunt.application.port.output.QuestPort;
import com.treasure_hunt.application.port.output.StepPort;
import com.treasure_hunt.application.port.output.UserPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AdventureMappingHelper {
    private final UserPort userPort;
    private final QuestPort questPort;
    private final StepPort stepPort;


    public AdventureMappingHelper(UserPort userPort, QuestPort questPort, StepPort stepPort) {
        this.userPort = userPort;
        this.questPort = questPort;
        this.stepPort = stepPort;
    }

    public User getAdventurer(UUID uuid) {
        return (uuid != null)
                ? userPort.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException("Adventurer not found."))
                : null;
    }

    public Quest getQuest(UUID uuid) {
        return (uuid != null)
                ? questPort.findByUuid(uuid).orElseThrow(() -> new QuestNotFoundException("Quest not found."))
                : null;
    }

    public Step getCurrentStep(Integer id) {
        return (id != null)
                ? stepPort.findById(id).orElseThrow(() -> new StepNotFoundException("Current step not found."))
                : null;
    }
}
