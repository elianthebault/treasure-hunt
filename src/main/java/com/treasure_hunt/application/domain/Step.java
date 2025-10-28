package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    private int id;
    private Integer questId;
    private Puzzle puzzle;
    private Step previousStep;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Boolean valid;
}
