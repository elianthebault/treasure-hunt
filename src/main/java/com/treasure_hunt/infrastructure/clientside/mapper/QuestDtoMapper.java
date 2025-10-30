package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { StepDtoMapper.class })
public interface QuestDtoMapper {
    @Mapping(target = "author", expression = "java(userUseCase.findByUuid(questRequestDto.authorUuid()))")
    Quest toQuest(QuestRequestDto questRequestDto);

    QuestResponseDto toQuestResponseDto(Quest quest);
}
