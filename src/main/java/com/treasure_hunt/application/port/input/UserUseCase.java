package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserUseCase {
    User login(String email, String password);
    User save(User user, MultipartFile image);
    User findById(int id);
    User findByUuid(UUID uuid);
    void deleteById(int id);
    void deleteByUuid(UUID uuid);
    User update(UUID uuid, User user, MultipartFile image);
    void changePasswordRequest(UUID uuid);
}
