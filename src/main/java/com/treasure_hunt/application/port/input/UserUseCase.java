package com.treasure_hunt.application.port.input;

import com.treasure_hunt.application.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserUseCase {
    User login(String email, String password);
    User save(User user, MultipartFile image);
    User findById(int id);
    void deleteById(int id);
    User update(int id, User user, MultipartFile image);
    void changePasswordRequest(int id);
}
