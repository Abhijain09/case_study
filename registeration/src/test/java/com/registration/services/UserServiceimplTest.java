
package com.registration.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.registration.model.User;

import com.registration.repository.UserRepository;
import java.util.*;

import jakarta.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.registration.exception.UserNotFoundException;
import com.registration.model.User;
import com.registration.repository.UserRepository;

class UserServiceimplTest {
	
	@InjectMocks
    private UserServiceimpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "John", 30, "john@example.com", "1234567890", "Address 1", "Male", "john123", "password"));
        userList.add(new User(2, "Jane", 25, "jane@example.com", "9876543210", "Address 2", "Female", "jane123", "password"));

        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getUsers();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    public void testAddUser() {
        // Arrange
        User newUser = new User(3, "Alice", 28, "alice@example.com", "5555555555", "Address 3", "Female", "alice123", "password");

        when(userRepository.save(newUser)).thenReturn(newUser);

        // Act
        User result = userService.addUser(newUser);

        // Assert
        assertEquals(newUser, result);
    }

    @Test
    public void testGetUserByUsername() {
        // Arrange
        String username = "john123";
        User user = new User(1, "John", 30, "john@example.com", "1234567890", "Address 1", "Male", username, "password");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUserByUsername(username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

   

   
    @Test
    public void testUpdateUser_UserNotFound() {
        // Arrange
        String username = "nonexistentUser";
        User updatedUser = new User(1, "John Updated", 32, "john@example.com", "1234567890", "New Address", "Male", username, "newPassword");

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.updateUser(username, updatedUser));
    }

    @Test
    public void testDeleteUserByUsername() throws UserNotFoundException {
        // Arrange
        String username = "john123";
        User existingUser = new User(1, "John", 30, "john@example.com", "1234567890", "Address 1", "Male", username, "password");

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        // Act
        userService.deleteUserByUsername(username);

        // Assert
        verify(userRepository, times(1)).delete(existingUser);
    }

    @Test
    public void testDeleteUserByUsername_UserNotFound() {
        // Arrange
        String username = "nonexistentUser";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> userService.deleteUserByUsername(username));
    }

	

}