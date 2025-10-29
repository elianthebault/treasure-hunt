package com.treasure_hunt.infrastructure.serverside.mapper;

import com.treasure_hunt.application.domain.Quest;
import com.treasure_hunt.infrastructure.serverside.entity.QuestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestEntityMapper {
    Quest toQuest(QuestEntity questEntity);
    QuestEntity toQuestEntity(Quest quest);
}
