package com.treasure_hunt.infrastructure.clientside.controller;

import com.treasure_hunt.application.domain.Adventure;
import com.treasure_hunt.application.port.input.AdventureUseCase;
import com.treasure_hunt.infrastructure.clientside.dto.adventure.AdventureRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.adventure.AdventureResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.AdventureDtoMapper;
import com.treasure_hunt.infrastructure.clientside.mapper.helper.AdventureMappingHelper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/adventures")
public class AdventureController {
    private final AdventureDtoMapper adventureDtoMapper;
    private final AdventureUseCase adventureUseCase;
    private final AdventureMappingHelper helper;

    public AdventureController(AdventureDtoMapper adventureDtoMapper, AdventureUseCase adventureUseCase, AdventureMappingHelper helper) {
        this.adventureDtoMapper = adventureDtoMapper;
        this.adventureUseCase = adventureUseCase;
        this.helper = helper;
    }

    @PostMapping
    public ResponseEntity<AdventureResponseDto> save(
            @RequestBody AdventureRequestDto adventureRequestDto
    ) {
        Adventure adventure = adventureDtoMapper.toAdventure(adventureRequestDto, helper);
        Adventure persisted = adventureUseCase.save(adventure);
        AdventureResponseDto adventureResponseDto = adventureDtoMapper.toAdventureResponseDto(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(adventureResponseDto);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AdventureResponseDto> findByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        Adventure adventure = adventureUseCase.findByUuid(uuid);
        AdventureResponseDto adventureResponseDto = adventureDtoMapper.toAdventureResponseDto(adventure);

        return ResponseEntity.ok().body(adventureResponseDto);
    }

    @GetMapping("/adventurer/{uuid}")
    public ResponseEntity<List<AdventureResponseDto>> findByAdventurerUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        List<AdventureResponseDto> list = adventureUseCase.findByAdventurerUuid(uuid)
                .stream()
                .map(adventureDtoMapper::toAdventureResponseDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteByUuid(
            @PathVariable("uuid") UUID uuid
    ) {
        adventureUseCase.deleteByUuid(uuid);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<AdventureResponseDto> update(
            @PathVariable("uuid") UUID uuid,
            @PathVariable("id") int id
    ) {
        Adventure updatedAdventure = adventureUseCase.update(uuid, id);
        AdventureResponseDto adventureResponseDto = adventureDtoMapper.toAdventureResponseDto(updatedAdventure);

        return ResponseEntity.ok(adventureResponseDto);
    }
}
