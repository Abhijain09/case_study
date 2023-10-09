package com.jwt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.controller.JwtController;
import com.jwt.model.JwtRequest;
import com.jwt.model.JwtResponse;
import com.jwt.services.CustomUserDetailsService;
import com.jwt.helper.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class JwtControllerTest {

	private JwtController jwtController;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jwtController = new JwtController(authenticationManager, customUserDetailsService, jwtUtil);
    }

    @Test
    public void testGenerateToken_Success() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("testuser", "password");
        UserDetails userDetails = new User("testuser", "password", new ArrayList<>());
        when(customUserDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtUtil.generateToken(userDetails)).thenReturn("sample-jwt-token");

        ResponseEntity<?> responseEntity = jwtController.generateToken(jwtRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Manually compare the fields of JwtResponse
        JwtResponse expectedResponse = new JwtResponse("sample-jwt-token");
        JwtResponse actualResponse = (JwtResponse) responseEntity.getBody();

        assertEquals(expectedResponse.getToken(), actualResponse.getToken());
    }

    @Test
    public void testGenerateToken_InvalidCredentials() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("invaliduser", "wrongpassword");
        when(authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken("invaliduser", "wrongpassword")))
            .thenThrow(new BadCredentialsException("Invalid credentials"));

        try {
            jwtController.generateToken(jwtRequest);
        } catch (Exception e) {
            assertEquals("Bad Credentials", e.getMessage());
        }
    }

    @Test
    public void testGenerateToken_UserNotFound() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("nonexistentuser", "password");
        when(customUserDetailsService.loadUserByUsername("nonexistentuser"))
            .thenThrow(new UsernameNotFoundException("User not found"));

        try {
            jwtController.generateToken(jwtRequest);
        } catch (Exception e) {
            assertEquals("User not found", e.getMessage()); // Update the expected message here
        }
    }
}