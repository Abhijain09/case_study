package com.jwt.services;

import static org.junit.jupiter.api.Assertions.*;
import com.jwt.model.CustomUserDetails;
import com.jwt.model.User;
import com.jwt.repo.UserRepository;
import com.jwt.services.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class CustomUserDetailsServiceTest {

	 private CustomUserDetailsService customUserDetailsService;

	    @Mock
	    private UserRepository userRepository;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	        customUserDetailsService = new CustomUserDetailsService(userRepository);

	    }

	    @Test
	    public void testLoadUserByUsername_UserFound() {
	        String username = "testuser";
	        User user = new User();
	        user.setUsername(username);
	        when(userRepository.findByUsername(username)).thenReturn(user);

	        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

	        assertNotNull(userDetails);
	        assertTrue(userDetails instanceof CustomUserDetails);
	        assertEquals(username, userDetails.getUsername());
	    }

	    @Test
	    public void testLoadUserByUsername_UserNotFound() {
	        String username = "nonexistentuser";
	        when(userRepository.findByUsername(username)).thenReturn(null);

	        assertThrows(UsernameNotFoundException.class, () -> {
	            customUserDetailsService.loadUserByUsername(username);
	        });
	    }

}