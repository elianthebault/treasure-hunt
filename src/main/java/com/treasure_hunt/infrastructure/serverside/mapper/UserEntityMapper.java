package com.treasure_hunt.infrastructure.serverside.mapper;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.infrastructure.serverside.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {
    User toUser(UserEntity userEntity);
    UserEntity toUserEntity(User user);
}