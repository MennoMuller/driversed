package com.driversed.driversed.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .and()

                .authorizeHttpRequests()
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")

                .requestMatchers("/studentauth/**")
                .hasRole("STUDENT")

                .requestMatchers("/instructorauth/**")
                .hasRole("INSTRUCTOR")

                .requestMatchers("/**")
                .permitAll()

                .and()
                .httpBasic();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN", "STUDENT", "INSTRUCTOR")
                        .build()
        );

        manager.createUser(
                User.withUsername("student")
                        .password(passwordEncoder.encode("password"))
                        .roles("STUDENT")
                        .build()
        );

        manager.createUser(
                User.withUsername("instructor")
                        .password(passwordEncoder.encode("password"))
                        .roles("INSTRUCTOR")
                        .build()
        );

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
