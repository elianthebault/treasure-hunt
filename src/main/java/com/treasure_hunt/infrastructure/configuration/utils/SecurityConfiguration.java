package com.treasure_hunt.infrastructure.configuration.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        /*
                        .requestMatchers(HttpMethod.GET, BOARDERS_PATH).permitAll()
                        .requestMatchers(HttpMethod.POST, BOARDERS_PATH).hasAnyRole(BOARDER, ADMIN)
                        .requestMatchers(HttpMethod.PATCH, BOARDERS_PATH).hasRole(BOARDER)
                        .requestMatchers(HttpMethod.DELETE, BOARDERS_PATH).hasAnyRole(BOARDER, ADMIN)

                        .requestMatchers(HttpMethod.GET, BOARDS_PATH).permitAll()
                        .requestMatchers(HttpMethod.POST, BOARDS_PATH).hasAnyRole(BOARDER, ADMIN)
                        .requestMatchers(HttpMethod.PATCH, BOARDS_PATH).hasRole(BOARDER)
                        .requestMatchers(HttpMethod.DELETE, BOARDS_PATH).hasAnyRole(BOARDER, ADMIN)

                        .requestMatchers(HttpMethod.GET, MESSAGES_PATH).permitAll()
                        .requestMatchers(HttpMethod.POST, MESSAGES_PATH).hasAnyRole(BOARDER, ADMIN)
                        .requestMatchers(HttpMethod.PATCH, MESSAGES_PATH).hasRole(BOARDER)
                        .requestMatchers(HttpMethod.DELETE, MESSAGES_PATH).hasAnyRole(BOARDER, ADMIN)

                        .anyRequest().authenticated()
                        */
                        .anyRequest().permitAll()
                );

        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8091"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //@Bean
    //UserDetailsManager userDetailsManager(DataSource dataSource) {
    //    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
    //    jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT name, password, 1 FROM boarders WHERE name = ?");
    //    jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT name, role FROM boarders WHERE name = ?");
    //    return jdbcUserDetailsManager;
    //}
}
