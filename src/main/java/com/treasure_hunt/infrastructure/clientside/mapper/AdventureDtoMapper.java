package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.Adventure;
import com.treasure_hunt.infrastructure.clientside.dto.adventure.AdventureRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.adventure.AdventureResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.helper.AdventureMappingHelper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = AdventureMappingHelper.class)
public interface AdventureDtoMapper {
    @Mapping(target = "adventurer", expression = "java(helper.getAdventurer(adventureRequestDto.adventurerUuid()))")
    @Mapping(target = "quest", expression = "java(helper.getQuest(adventureRequestDto.questUuid()))")
    @Mapping(target = "currentStep", expression = "java(helper.getCurrentStep(adventureRequestDto.currentStepId()))")
    Adventure toAdventure(AdventureRequestDto adventureRequestDto, @Context AdventureMappingHelper helper);

    AdventureResponseDto toAdventureResponseDto(Adventure quest);
}
