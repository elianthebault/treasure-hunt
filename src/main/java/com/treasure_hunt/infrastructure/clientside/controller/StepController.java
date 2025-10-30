package com.treasure_hunt.infrastructure.clientside.controller;

import com.treasure_hunt.application.domain.Step;
import com.treasure_hunt.application.port.input.StepUseCase;
import com.treasure_hunt.infrastructure.clientside.dto.step.StepRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.step.StepResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.StepDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/steps")
public class StepController {
    private final StepDtoMapper stepDtoMapper;
    private final StepUseCase stepUseCase;

    public StepController(StepDtoMapper stepDtoMapper, StepUseCase stepUseCase) {
        this.stepDtoMapper = stepDtoMapper;
        this.stepUseCase = stepUseCase;
    }

    @PostMapping
    public ResponseEntity<StepResponseDto> save(
            @RequestBody StepRequestDto stepRequestDto
    ) {
        Step step = stepDtoMapper.toStep(stepRequestDto);
        Step persisted = stepUseCase.save(step);
        StepResponseDto stepResponseDto = stepDtoMapper.toStepResponseDto(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(stepResponseDto);
    }

    @GetMapping("/quest/{uuid}")
    public ResponseEntity<List<StepResponseDto>> findByQuestUuidOrderByStepNumberAsc(
            @PathVariable("uuid") UUID uuid
    ) {
        List<StepResponseDto> list = stepUseCase.findByQuestUuidOrderByStepNumberAsc(uuid)
                .stream()
                .map(stepDtoMapper::toStepResponseDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StepResponseDto> findById(
            @PathVariable("id") int id
    ) {
        Step step = stepUseCase.findById(id);
        StepResponseDto stepResponseDto = stepDtoMapper.toStepResponseDto(step);

        return ResponseEntity.ok().body(stepResponseDto);
    }

    @GetMapping("/next/{id}")
    public ResponseEntity<StepResponseDto> findByPreviousStepId(
            @PathVariable("id") int id
    ) {
        Step step = stepUseCase.findByPreviousStepId(id);
        StepResponseDto stepResponseDto = stepDtoMapper.toStepResponseDto(step);

        return ResponseEntity.ok().body(stepResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") int id
    ) {
        stepUseCase.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<StepResponseDto> update(
            @PathVariable("id") int id,
            @RequestBody StepRequestDto stepRequestDto
    ) {
        Step step = stepDtoMapper.toStep(stepRequestDto);
        Step updatedStep = stepUseCase.update(id, step);
        StepResponseDto stepResponseDto = stepDtoMapper.toStepResponseDto(updatedStep);

        return ResponseEntity.ok(stepResponseDto);
    }
}
