package com.treasure_hunt.infrastructure.serverside.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "steps")
public class StepEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    private QuestEntity quest;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "puzzle_id")
    private PuzzleEntity puzzle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "previous_step_id")
    private StepEntity previousStep;

    @Column(nullable = false)
    private Integer stepNumber;
    private Double longitude;
    private Double latitude;
    private Double altitude;
    private Boolean valid;

    public StepEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    public PuzzleEntity getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(PuzzleEntity puzzle) {
        this.puzzle = puzzle;
    }

    public StepEntity getPreviousStep() {
        return previousStep;
    }

    public void setPreviousStep(StepEntity previousStep) {
        this.previousStep = previousStep;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getAltitude() {
        return altitude;
    }

    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
