package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    private int id;
    private UUID uuid;
    private User author;
    private String name;
    private String lore;
    private Step firstStep;
    private Boolean valid;
}