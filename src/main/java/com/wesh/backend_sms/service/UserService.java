package com.wesh.backend_sms.service;

import org.springframework.stereotype.Service;

import com.wesh.backend_sms.entity.User;
import com.wesh.backend_sms.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
}
