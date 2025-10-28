package com.treasure_hunt.application.service;

import com.treasure_hunt.application.domain.User;
import com.treasure_hunt.application.port.output.UserPort;
import com.treasure_hunt.application.service.utils.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserPort userPort;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private ImageService imageService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void init() {
        userService = new UserService(userPort, passwordEncoder, imageService);
    }

    @Test
    void loginTest() {
        int id = 1;
        String email = "allo@mail.fr";
        String password = "123";

        User expected = create();
        expected.setId(id);

        when(userPort.findByEmail(email)).thenReturn(Optional.of(expected));
        when(passwordEncoder.matches(password, expected.getPassword())).thenReturn(true);

        User result = userService.login(email, password);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void saveTest() {
        int id = 1;

        User input = create();
        User persisted = new User();
        persisted.setId(id);

        when(userPort.save(input)).thenReturn(persisted);

        User result = userService.save(input, null);

        assertNotNull(result);
        assertEquals(id, result.getId());

        verify(userPort).save(input);
    }

    @Test
    void findByIdTest() {
        int id = 1;

        User expected = create();
        expected.setId(id);

        when(userPort.findById(id)).thenReturn(Optional.of(expected));

        User result = userService.findById(id);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void deleteByIdTest() {
        int id = 1;

        when(userPort.existsById(id)).thenReturn(true);

        userService.deleteById(id);

        verify(userPort).existsById(id);
        verify(userPort).deleteById(id);
    }

    @Test
    void updateTest() {
        int id = 1;
        String email = "aluile@mail.fr";

        User existing = create();
        existing.setId(id);

        User input = create();
        input.setId(id);
        input.setEmail(email);

        User persisted = create();
        persisted.setId(id);
        persisted.setEmail(email);

        when(userPort.existsById(id)).thenReturn(true);
        when(userPort.findById(id)).thenReturn(Optional.of(existing));
        when(userPort.save(existing)).thenReturn(persisted);

        User result = userService.update(id, input, null);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(email, result.getEmail());

        verify(userPort).existsById(id);
        verify(userPort).findById(id);
        verify(userPort).save(existing);
    }

    /*
    PRIVATE METHODS
     */

    private User create() {
        User user = new User();

        user.setPhoneNumber("0123456789");
        user.setEmail("allo@mail.fr");
        user.setFirstname("Allo");
        user.setLastname("Aluile");
        user.setNickname("Alaide");
        user.setProfile("");
        user.setPassword("123");

        return user;
    }
}
