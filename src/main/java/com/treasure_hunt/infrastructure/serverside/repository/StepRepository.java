package com.treasure_hunt.infrastructure.serverside.repository;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.infrastructure.serverside.entity.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StepRepository extends JpaRepository<StepEntity, Integer> {
    List<StepEntity> findByQuestUuidOrderByStepNumberAsc(UUID uuid);
    Optional<StepEntity> findByPreviousStepId(int id);
    void deleteById(int id);
    boolean existsById(int id);
}