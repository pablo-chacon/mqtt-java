package com.example.gpxlocationmqtt;

import com.example.gpxlocationmqtt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JwtUtilTest {


    @Autowired
    private JwtUtil jwtUtil;
    @Test
    public void testGenerateToken() {
        String token = jwtUtil.generateToken("testUser");
        assertNotNull(token);
    }

    @Test
    public void testGetClaimsFromToken() {
        String token = jwtUtil.generateToken("testUser");
        Claims claims = jwtUtil.getClaimsFromToken(token);
        assertEquals("testUser", claims.getSubject());
    }

    @Test
    public void testValidateToken() {
        String token = jwtUtil.generateToken("testUser");
        assertTrue(jwtUtil.validateToken(token));
    }

    @Test
    public void testGetUsernameFromToken() {
        String token = jwtUtil.generateToken("testUser");
        String username = jwtUtil.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }
}
