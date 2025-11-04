package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.quest.QuestResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.helper.QuestMappingHelper;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = QuestMappingHelper.class)
public interface QuestDtoMapper {
    @Mapping(target = "author", expression = "java(helper.getAuthor(questRequestDto.authorUuid()))")
    Quest toQuest(QuestRequestDto questRequestDto, @Context QuestMappingHelper helper);

    QuestResponseDto toQuestResponseDto(Quest quest);
}
