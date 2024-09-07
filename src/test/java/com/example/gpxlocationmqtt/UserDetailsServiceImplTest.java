package com.example.gpxlocationmqtt;

import com.example.gpxlocationmqtt.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class UserDetailsServiceImplTest {

    private final UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

    @Test
    public void testLoadUserByUsername_Success() {
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");
        assertNotNull(userDetails);
        assertEquals("testUser", userDetails.getUsername());
    }

    @Test
    public void testLoadUserByUsername_Failure() {
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername(null);
        });
    }
}
