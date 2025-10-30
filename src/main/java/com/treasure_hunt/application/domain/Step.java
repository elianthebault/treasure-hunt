package com.treasure_hunt.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    private int id;
    private UUID questUuid;
    private Puzzle puzzle;
    private Integer stepNumber;
    private Step previousStep;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Boolean valid;
}
