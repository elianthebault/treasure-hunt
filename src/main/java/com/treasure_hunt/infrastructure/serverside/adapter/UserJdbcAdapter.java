package com.treasure_hunt.infrastructure.serverside.adapter;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.port.output.UserPort;
import com.treasure_hunt.infrastructure.serverside.entity.UserEntity;
import com.treasure_hunt.infrastructure.serverside.mapper.UserEntityMapper;
import com.treasure_hunt.infrastructure.serverside.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserJdbcAdapter implements UserPort {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserJdbcAdapter(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userEntityMapper.toUserEntity(user);
        UserEntity persisted = userRepository.save(entity);
        User result = userEntityMapper.toUser(persisted);

        return result;
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);

        return optional.map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findByUuid(UUID uuid) {
        Optional<UserEntity> optional = userRepository.findByUuid(uuid);

        return optional.map(userEntityMapper::toUser);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        userRepository.deleteByUuid(uuid);
    }

    @Override
    public boolean existsById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean existsByUuid(UUID uuid) {
        return userRepository.existsByUuid(uuid);
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findByNicknameContainingIgnoreCase(String nickname) {
        return userRepository.findByNicknameContainingIgnoreCase(nickname)
                .stream()
                .map(userEntityMapper::toUser)
                .toList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> optional = userRepository.findByEmail(email);

        return optional.map(userEntityMapper::toUser);
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> optional = userRepository.findByPhoneNumber(phoneNumber);

        return optional.map(userEntityMapper::toUser);
    }
}
