package com.wesh.backend_sms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.wesh.backend_sms.entity.Message;
import com.wesh.backend_sms.entity.User;
import com.wesh.backend_sms.model.MessageDTO;
import com.wesh.backend_sms.model.SMSRequest;
import com.wesh.backend_sms.repository.MessageRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MessageService {
    private MessageRepository messageRepository;
    private UserService userService;

    public List<MessageDTO> sendMessage(String email, SMSRequest smsRequest) {
        User user = userService.getUserByEmail(email);

        if(user == null){
            System.out.println("return error");
        }

        List<MessageDTO> messagesSent = new ArrayList<>();
        Message message;
        Message messageSaved;
        int count = 0;

        if(smsRequest.getMessage().length() > 160) {
            // Gets any character grouping them between 1 to 160 chars
            Pattern pattern = Pattern.compile(".{1,160}"); // match 1 to 5 chars
            Matcher matcher = pattern.matcher(smsRequest.getMessage());

            while (matcher.find()) {
                    count++;

                    message = Message.builder()
                    .countryCode(smsRequest.getCountryCode())
                    .phone(smsRequest.getPhone())
                    .part(String.valueOf(count))
                    .text(matcher.group())
                    .user(user)
                    .build();
                    
                    messageSaved = messageRepository.save(message);

                    ModelMapper modelMapper = new ModelMapper();
                    MessageDTO messageConverted = modelMapper.map(messageSaved, MessageDTO.class);
                    
                    messagesSent.add(messageConverted);

                    if(messageSaved == null){
                        System.out.println("return error");
                    }   
            }
        } else {
                message = Message.builder()
                .countryCode(smsRequest.getCountryCode())
                .phone(smsRequest.getPhone())
                .part(String.valueOf(count))
                .text(smsRequest.getMessage())
                .user(user)
                .build();
                
                messageSaved = messageRepository.save(message);

                ModelMapper modelMapper = new ModelMapper();
                MessageDTO messageConverted = modelMapper.map(messageSaved, MessageDTO.class);

                messagesSent.add(messageConverted);

                if(messageSaved == null){
                    System.out.println("return error");
                }
        }

        return messagesSent;
    }
    
}
