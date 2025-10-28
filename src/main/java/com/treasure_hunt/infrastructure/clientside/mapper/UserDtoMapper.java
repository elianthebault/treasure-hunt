package com.treasure_hunt.infrastructure.clientside.mapper;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {
    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(User user);
}
