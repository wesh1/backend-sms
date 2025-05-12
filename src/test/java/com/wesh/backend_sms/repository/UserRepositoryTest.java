package com.wesh.backend_sms.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wesh.backend_sms.entity.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        User user = new User();
        
        user.setName("Test User");
        user.setEmail("testUser@example.com");
        user.setPassword("TestPassword123");

        userRepository.save(user);

        User result = userRepository.findByEmail("testUser@example.com");

        assertNotNull(result);
        assertThat(result.getEmail()).isEqualTo("testUser@example.com");
    }
    
}
