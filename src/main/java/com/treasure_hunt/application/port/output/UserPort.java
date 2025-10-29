package com.treasure_hunt.application.port.output;

import com.treasure_hunt.application.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserPort {
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUuid(UUID uuid);
    void deleteById(int id);
    boolean existsById(int id);
    boolean existsByNickname(String nickname);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    List<User> findByNicknameContainingIgnoreCase(String nickname);
    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
