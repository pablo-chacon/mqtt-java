package com.example.gpxlocationmqtt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Generates JWT token for the specified username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // Retrieves claims from the JWT token
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Validates if the token has expired
    public boolean validateToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().after(new Date());
    }

    // Extracts the username from the token
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }
}
