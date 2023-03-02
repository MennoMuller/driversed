package com.driversed.driversed.configuration;

import com.driversed.driversed.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()

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
                .userDetailsService(myUserDetailsService)
                .httpBasic();
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(
//                User.withUsername("admin")
//                        .password(passwordEncoder.encode("admin"))
//                        .roles("ADMIN", "STUDENT", "INSTRUCTOR")
//                        .build()
//        );
//
//        manager.createUser(
//                User.withUsername("student")
//                        .password(passwordEncoder.encode("password"))
//                        .roles("STUDENT")
//                        .build()
//        );
//
//        manager.createUser(
//                User.withUsername("instructor")
//                        .password(passwordEncoder.encode("password"))
//                        .roles("INSTRUCTOR")
//                        .build()
//        );
//
//        return manager;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
