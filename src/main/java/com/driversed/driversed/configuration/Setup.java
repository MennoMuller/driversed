package com.driversed.driversed.configuration;

import com.driversed.driversed.model.User;
import com.driversed.driversed.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Setup {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        User user = new User("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN,ROLE_STUDENT,ROLE_INSTRUCTOR");
        userRepository.save(user);
    }
}
