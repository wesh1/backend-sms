package com.wesh.backend_sms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO {
    private String countryCode;
    private String phone;
    private String text;
    private String part;
}
