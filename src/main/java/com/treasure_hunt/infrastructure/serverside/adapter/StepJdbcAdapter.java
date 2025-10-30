package com.treasure_hunt.infrastructure.serverside.adapter;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.port.output.StepPort;
import com.treasure_hunt.infrastructure.serverside.entity.StepEntity;
import com.treasure_hunt.infrastructure.serverside.mapper.StepEntityMapper;
import com.treasure_hunt.infrastructure.serverside.repository.StepRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StepJdbcAdapter implements StepPort {
    private final StepRepository stepRepository;
    private final StepEntityMapper stepEntityMapper;

    public StepJdbcAdapter(StepRepository stepRepository, StepEntityMapper stepEntityMapper) {
        this.stepRepository = stepRepository;
        this.stepEntityMapper = stepEntityMapper;
    }

    @Override
    public Step save(Step step) {
        StepEntity entity = stepEntityMapper.toStepEntity(step);
        StepEntity persisted = stepRepository.save(entity);
        Step result = stepEntityMapper.toStep(persisted);

        return result;
    }

    @Override
    public List<Step> findByQuestUuidOrderByStepNumberAsc(UUID uuid) {
        return stepRepository.findByQuestUuidOrderByStepNumberAsc(uuid)
                .stream()
                .map(stepEntityMapper::toStep)
                .toList();
    }

    @Override
    public Optional<Step> findById(int id) {
        Optional<StepEntity> optional = stepRepository.findById(id);

        return optional.map(stepEntityMapper::toStep);
    }

    @Override
    public Optional<Step> findByPreviousStepId(int id) {
        Optional<StepEntity> optional = stepRepository.findByPreviousStepId(id);

        return optional.map(stepEntityMapper::toStep);
    }

    @Override
    public void deleteById(int id) {
        stepRepository.deleteById(id);
    }

    @Override
    public boolean existsById(int id) {
        return stepRepository.existsById(id);
    }
}
