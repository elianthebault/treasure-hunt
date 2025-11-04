package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adventure {
    private int id;
    private UUID uuid;
    private User adventurer;
    private Quest quest;
    private Step currentStep;
}
