package com.treasure_hunt.infrastructure.serverside.mapper;

import com.treasure_hunt.application.domain.Adventure;
import com.treasure_hunt.infrastructure.serverside.entity.AdventureEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdventureEntityMapper {
    Adventure toAdventure(AdventureEntity adventureEntity);
    AdventureEntity toAdventureEntity(Adventure adventure);
}
