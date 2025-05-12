package com.wesh.backend_sms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.wesh.backend_sms.entity.Message;
import com.wesh.backend_sms.entity.User;

@DataJpaTest
public class MessageServiceRepositoryTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void itShouldSaveMessage() {
        User user = new User();
        
        user.setName("Test User");
        user.setEmail("testUser@example.com");
        user.setPassword("TestPassword123");

        userRepository.save(user);

        User result = userRepository.findByEmail("testUser@example.com");

        Message message = new Message();
        message.setCountryCode("+502");
        message.setPhone("30303030");
        message.setText("SMS test");
        message.setPart("0");
        message.setUser(result);

        var messageSaved = messageRepository.save(message);
        assertThat(messageSaved.getId() != null);
    }

}
