package com.wesh.backend_sms.auth;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    private String jwtSecret = "Z3J2Q0ZkSGhIRW1LaHgxQUlYRTJBZXBxSW4wRnFVcWk=";

    private static final long EXPIRATION_TIME = 60 * 60 * 1000; // 1 hour
 
    // Generate a JWT token
    public String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
    // Validate a token
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
                
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey()).build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

}
