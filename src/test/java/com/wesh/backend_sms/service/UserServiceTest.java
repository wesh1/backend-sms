package com.wesh.backend_sms.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.wesh.backend_sms.entity.User;
import com.wesh.backend_sms.repository.UserRepository;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUserWhenEmailExist() {
        User mockUser = new User("Test User", "test@example.com", "PasswordTest123");
        when(userRepository.findByEmail("test@example.com")).thenReturn(mockUser);

        User result = userService.getUserByEmail("test@example.com");

        assertThat(result.getEmail()).isEqualTo("test@example.com");

        verify(userRepository).findByEmail("test@example.com");
    }

}
