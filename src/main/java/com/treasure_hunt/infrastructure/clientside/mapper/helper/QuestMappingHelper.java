package com.treasure_hunt.infrastructure.clientside.mapper.helper;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.exception.StepNotFoundException;
import com.treasure_hunt.application.exception.UserNotFoundException;
import com.treasure_hunt.application.port.output.StepPort;
import com.treasure_hunt.application.port.output.UserPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class QuestMappingHelper {
    private final UserPort userPort;
    private final StepPort stepPort;


    public QuestMappingHelper(UserPort userPort, StepPort stepPort) {
        this.userPort = userPort;
        this.stepPort = stepPort;
    }

    public User getAuthor(UUID uuid) {
        return (uuid != null)
                ? userPort.findByUuid(uuid).orElseThrow(() -> new UserNotFoundException("Author not found."))
                : null;
    }

    public Step getFirstStep(Integer id) {
        return (id != null)
                ? stepPort.findById(id).orElseThrow(() -> new StepNotFoundException("First step not found."))
                : null;
    }
}
