package com.wesh.backend_sms.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wesh.backend_sms.model.MessageDTO;
import com.wesh.backend_sms.model.SMSRequest;
import com.wesh.backend_sms.service.MessageService;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/protected")
public class SmsController {
    private MessageService messageService;
    
    @PostMapping("/send-sms")
    public List<MessageDTO> sendSMS(HttpServletRequest request, @RequestBody SMSRequest smsRequest) {
        String email = (String) request.getAttribute("email");

        List<MessageDTO> messagesSent = messageService.sendMessage(email, smsRequest);
        
        ObjectMapper mapper = new ObjectMapper();

        // Converts to JSON to show in the console
        try {
            String json = mapper.writeValueAsString(messagesSent);
            log.info("Messages sent: {}", json);
        } catch (JsonProcessingException e) {
            log.error("Error while converting to JSON", e);
        }

        return messagesSent;
    }
    
}
