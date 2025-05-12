package com.wesh.backend_sms.model;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;

}
