package com.wesh.backend_sms.controller;

import org.springframework.web.bind.annotation.*;

import com.wesh.backend_sms.auth.JwtTokenUtil;
import com.wesh.backend_sms.entity.User;
import com.wesh.backend_sms.model.LoginRequest;
import com.wesh.backend_sms.model.LoginResponse;
import com.wesh.backend_sms.service.UserService;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.getUserByEmail(request.getEmail());
        
        if (user != null && user.getEmail().equals(request.getEmail()) && user.getPassword().equals(request.getPassword())) {
            String token = jwtTokenUtil.generateToken(request.getEmail());
            
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        
    }
    
}
