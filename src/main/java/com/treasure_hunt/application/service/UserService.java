package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.exception.ImageException;
import com.treasure_hunt.application.exception.UserException;
import com.treasure_hunt.application.exception.UserNotFoundException;
import com.treasure_hunt.application.port.input.UserUseCase;
import com.treasure_hunt.application.port.output.UserPort;
import com.treasure_hunt.application.service.utils.ImageService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserService implements UserUseCase {
    private static final String NOT_FOUND = "User not found.";
    
    private final UserPort userPort;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    public UserService(UserPort userPort, PasswordEncoder passwordEncoder, ImageService imageService) {
        this.userPort = userPort;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @Override
    public User login(String email, String password) {
        return checkLogin(email, password);
    }

    @Override
    public User save(User user, MultipartFile image) {
        verifyUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userPort.save(user);

        if (image != null) {
            savedUser.setProfile(uploadImage(image, savedUser.getId()));
            return userPort.save(savedUser);
        }

        return savedUser;
    }

    @Override
    public User findById(int id) {
        Optional<User> optionalUser = userPort.findById(id);

        if (optionalUser.isEmpty())
            throw new UserNotFoundException(NOT_FOUND);

        return optionalUser.get();
    }

    @Override
    public User findByUuid(UUID uuid) {
        Optional<User> optionalUser = userPort.findByUuid(uuid);

        if (optionalUser.isEmpty())
            throw new UserNotFoundException(NOT_FOUND);

        return optionalUser.get();
    }

    @Override
    public void deleteById(int id) {
        if (!userPort.existsById(id))
            throw new UserNotFoundException(NOT_FOUND);

        userPort.deleteById(id);
    }

    @Override
    public User update(int id, User newUser, MultipartFile image) {
        if (!userPort.existsById(id))
            throw new UserNotFoundException(NOT_FOUND);

        User userToUpdate = findById(id);

        compareUser(userToUpdate, newUser);

        if (image != null) {
            deleteProfile(userToUpdate.getProfile());
            userToUpdate.setProfile(uploadImage(image, userToUpdate.getId()));
        }

        return userPort.save(userToUpdate);
    }

    @Override
    public void changePasswordRequest(int id) {
        if (!userPort.existsById(id))
            throw new UserNotFoundException(NOT_FOUND);

        User userToUpdate = findById(id);

        //TODO
        //resetPasswordService.sendResetMail(userToUpdate.getEmail());
    }

    /*
    PRIVATE METHODS
     */

    private User checkLogin(String email, String password) {
        Optional<User> optionalUser = userPort.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException(NOT_FOUND);
        if (!passwordEncoder.matches(password, optionalUser.get().getPassword()))
            throw new UserException("Invalid password.");
        return optionalUser.get();
    }

    private void verifyUser(User user) {
        checkUser(user);
        checkExists(user);
    }

    private static void checkUser(User user) {
        if (user == null)
            throw new UserException("User is null.");
        if (user.getId() != 0)
            throw new UserException("Id is different from 0(zero).");
        if (user.getFirstname() == null || user.getFirstname().isBlank())
            throw new UserException("Firstname is null or blank.");
        if (user.getLastname() == null || user.getLastname().isBlank())
            throw new UserException("Lastname is null or blank.");
        if (user.getNickname() == null || user.getNickname().isBlank())
            throw new UserException("Nickname is null or blank.");
        if (user.getPassword() == null || user.getPassword().isBlank())
            throw new UserException("Password is null or blank.");
    }

    private void checkExists(User user) {
        if (userPort.existsByEmail(user.getEmail()))
            throw new UserException("Email already exists.");
        if (userPort.existsByPhoneNumber(user.getPhoneNumber()))
            throw new UserException("Phone number already exists.");
        if (userPort.existsByNickname(user.getNickname()))
            throw new UserException("Nickname already exists.");
    }

    private String uploadImage(MultipartFile image, int id) {
        String imagePath = null;

        Map<String, Object> infos = new HashMap<>();
        infos.put("type", "profile");
        infos.put("userId", id);

        try {
            imagePath = imageService.processImage(image, infos);
        }catch (IOException e) {
            throw new ImageException(e.getMessage());
        }

        return imagePath;
    }

    private void compareUser(User userToUpdate, User newUser) {
        if (newUser.getPhoneNumber() != null
                && !newUser.getPhoneNumber().isBlank()
                && !newUser.getPhoneNumber().equals(userToUpdate.getPhoneNumber()))
            userToUpdate.setPhoneNumber(newUser.getPhoneNumber());
        if (newUser.getEmail() != null
                && !newUser.getEmail().isBlank()
                && !newUser.getEmail().equals(userToUpdate.getEmail()))
            userToUpdate.setEmail(newUser.getEmail());
        if (newUser.getFirstname() != null
                && !newUser.getFirstname().isBlank()
                && !newUser.getFirstname().equals(userToUpdate.getFirstname()))
            userToUpdate.setFirstname(newUser.getFirstname());
        if (newUser.getLastname() != null
                && !newUser.getLastname().isBlank()
                && !newUser.getLastname().equals(userToUpdate.getLastname()))
            userToUpdate.setLastname(newUser.getLastname());
        if (newUser.getNickname() != null
                && !newUser.getNickname().isBlank()
                && !newUser.getNickname().equals(userToUpdate.getNickname()))
            userToUpdate.setNickname(newUser.getNickname());
    }

    private void deleteProfile(String profilePath) {
        imageService.deleteImage(profilePath);
    }
}
