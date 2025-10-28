package com.treasure_hunt.infrastructure.configuration;

import com.treasure_hunt.application.port.input.UserUseCase;
import com.treasure_hunt.application.port.output.UserPort;
import com.treasure_hunt.application.service.UserService;
import com.treasure_hunt.application.service.utils.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {
    @Bean
    public UserUseCase getUserUseCase(UserPort userPort, PasswordEncoder passwordEncoder, ImageService imageService) {
        return new UserService(userPort, passwordEncoder, imageService);
    }
}
