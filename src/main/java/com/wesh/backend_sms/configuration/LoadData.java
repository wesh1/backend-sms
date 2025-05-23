package com.wesh.backend_sms.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wesh.backend_sms.entity.User;
import com.wesh.backend_sms.repository.UserRepository;

@Configuration
public class LoadData {

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {
        return args -> {
            repository.save(new User("William", "william@guatemala502.com", "MyPass1234"));
            repository.save(new User("Samsung", "samsung@guatemala502.com", "Walk1234"));
            repository.save(new User("Pixel", "Pixel@guatemala502.com", "Sing1234"));
        };
    }
    
}
