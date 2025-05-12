package com.wesh.backend_sms.model;

import lombok.Data;

@Data
public class SMSRequest {
    private String countryCode;
    private String phone;
    private String message;
}
