package com.treasure_hunt.infrastructure.serverside.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "adventures")
public class AdventureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adventurer_id", nullable = false)
    private UserEntity adventurer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id", nullable = false)
    private QuestEntity quest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_step_id")
    private StepEntity currentStep;

    public AdventureEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UserEntity getAdventurer() {
        return adventurer;
    }

    public void setAdventurer(UserEntity adventurer) {
        this.adventurer = adventurer;
    }

    public QuestEntity getQuest() {
        return quest;
    }

    public void setQuest(QuestEntity quest) {
        this.quest = quest;
    }

    public StepEntity getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(StepEntity currentStep) {
        this.currentStep = currentStep;
    }
}
