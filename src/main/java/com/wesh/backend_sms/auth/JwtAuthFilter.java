package com.wesh.backend_sms.auth;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthFilter implements Filter {
    private JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Only secure certain endpoints
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/api/protected")) {
            String authHeader = httpRequest.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Missing or invalid Authorization header");
                return;
            }

            // Remove "Bearer "
            String token = authHeader.substring(7);

            if (!jwtTokenUtil.validateToken(token)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Invalid or expired token");
                return;
            }

            // Set user info as a request attribute
            String email = jwtTokenUtil.getEmailFromToken(token);
            request.setAttribute("email", email);
        }

        chain.doFilter(request, response);
    }
    
}
