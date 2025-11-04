package com.treasure_hunt.infrastructure.clientside.controller;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.application.port.input.QuestUseCase;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.QuestDtoMapper;
import com.treasure_hunt.infrastructure.clientside.mapper.helper.QuestMappingHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/quests")
public class QuestController {
    private final QuestDtoMapper questDtoMapper;
    private final QuestUseCase questUseCase;
    private final QuestMappingHelper helper;

    public QuestController(QuestDtoMapper questDtoMapper, QuestUseCase questUseCase, QuestMappingHelper helper) {
        this.questDtoMapper = questDtoMapper;
        this.questUseCase = questUseCase;
        this.helper = helper;
    }

    @PostMapping
    public ResponseEntity<QuestResponseDto> save(
            @RequestBody QuestRequestDto questRequestDto
    ) {
        Quest quest = questDtoMapper.toQuest(questRequestDto, helper);
        Quest persisted = questUseCase.save(quest);
        QuestResponseDto questResponseDto = questDtoMapper.toQuestResponseDto(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(questResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<QuestResponseDto>> findAll() {
        List<QuestResponseDto> list = questUseCase.findAll()
                .stream()
                .map(questDtoMapper::toQuestResponseDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<QuestResponseDto> findByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        Quest quest = questUseCase.findByUuid(uuid);
        QuestResponseDto questResponseDto = questDtoMapper.toQuestResponseDto(quest);

        return ResponseEntity.ok().body(questResponseDto);
    }

    @GetMapping("/by/{uuid}")
    public ResponseEntity<List<QuestResponseDto>> findByAuthorUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        List<QuestResponseDto> list = questUseCase.findByAuthorUuid(uuid)
                .stream()
                .map(questDtoMapper::toQuestResponseDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/search")
    public ResponseEntity<List<QuestResponseDto>> findByNameAndLore(
            @RequestParam("search") String search
    ) {
        List<QuestResponseDto> result = questUseCase.findByNameAndLore(search)
                .stream()
                .map(questDtoMapper::toQuestResponseDto)
                .toList();

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        questUseCase.deleteByUuid(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<QuestResponseDto> update(
            @PathVariable("uuid") UUID uuid,
            @RequestBody QuestRequestDto questRequestDto
    ) {
        Quest quest = questDtoMapper.toQuest(questRequestDto, helper);
        Quest updatedQuest = questUseCase.update(uuid, quest);
        QuestResponseDto questResponseDto = questDtoMapper.toQuestResponseDto(updatedQuest);

        return ResponseEntity.ok(questResponseDto);
    }
}
