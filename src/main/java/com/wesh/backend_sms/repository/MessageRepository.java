package com.wesh.backend_sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wesh.backend_sms.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
