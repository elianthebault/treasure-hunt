package com.treasure_hunt.infrastructure.serverside.repository;

import com.treasure_hunt.infrastructure.serverside.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByNickname(String nickname);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    boolean existsByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
    List<UserEntity> findByNicknameContainingIgnoreCase(String nickname);
    Optional<UserEntity> findByUuid(UUID uuid);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
