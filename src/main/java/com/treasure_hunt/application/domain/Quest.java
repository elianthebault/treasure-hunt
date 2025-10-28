package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quest {
    private int id;
    private User author;
    private String name;
    private String lore;
    private Step firstStep;
    private Boolean valid;
}