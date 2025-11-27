package com.treasure_hunt.infrastructure.clientside.controller;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.port.input.UserUseCase;
import com.treasure_hunt.infrastructure.clientside.dto.user.LoginRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserRequestDto;
import com.treasure_hunt.infrastructure.clientside.dto.user.UserResponseDto;
import com.treasure_hunt.infrastructure.clientside.mapper.UserDtoMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserDtoMapper userDtoMapper;
    private final UserUseCase userUseCase;

    public UserController(UserDtoMapper userDtoMapper, UserUseCase userUseCase) {
        this.userDtoMapper = userDtoMapper;
        this.userUseCase = userUseCase;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Allow a user to log in the app.")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) {
        User user = userUseCase.login(loginRequestDto.email(), loginRequestDto.password());
        UserResponseDto userResponseDto = userDtoMapper.toUserResponseDto(user);

        return ResponseEntity.ok(userResponseDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> save(
            @RequestPart("user") UserRequestDto user,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        User persisted = userUseCase.save(userDtoMapper.toUser(user), image);
        UserResponseDto response = userDtoMapper.toUserResponseDto(persisted);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }




    @GetMapping("/{id}")
    @Operation(summary = "Find by id", description = "To find a user using his ID.")
    public ResponseEntity<UserResponseDto> findById(
            @PathVariable("id") int id
    ) {
        User user = userUseCase.findById(id);
        UserResponseDto userResponseDto = userDtoMapper.toUserResponseDto(user);

        return ResponseEntity.ok().body(userResponseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete by id", description = "To delete a user using his ID.")
    public ResponseEntity<Void> deleteById(
            @PathVariable("id") int id
    ) {
        userUseCase.deleteById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update user", description = "To update user infos.")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable("uuid") UUID uuid,
            @RequestBody UserRequestDto userRequestDto,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        User user = userDtoMapper.toUser(userRequestDto);
        User updatedUser = userUseCase.update(uuid, user, image);
        UserResponseDto userResponseDto = userDtoMapper.toUserResponseDto(updatedUser);

        return ResponseEntity.ok(userResponseDto);
    }
}
