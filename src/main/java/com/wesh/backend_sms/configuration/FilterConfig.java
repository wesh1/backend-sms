package com.wesh.backend_sms.configuration;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import com.wesh.backend_sms.auth.JwtAuthFilter;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<Filter> jwtFilter(JwtAuthFilter jwtAuthFilter) {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(jwtAuthFilter);
        registrationBean.addUrlPatterns("/api/protected/*"); // Protect these paths
        return registrationBean;
    }
    
}
