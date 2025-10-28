package com.treasure_hunt.infrastructure.configuration.utils;

import com.treasure_hunt.application.service.utils.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageConfiguration {
    @Bean
    public ImageService getImageService() {
        return new ImageService();
    }
}
