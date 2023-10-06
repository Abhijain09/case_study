package com.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.registration.exception.UserNotFoundException;
import com.registration.model.User;
import com.registration.repository.UserRepository;
import com.registration.services.UserServiceimpl;

public class UserServiceTest {
	
	
	@InjectMocks
    private UserServiceimpl userServiceImpl;

    @Mock
    private UserRepository userRepo;
    
    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

 // Test user creation and validate the result
    @Test
    public void testCreateUser() {
    	
        User user = new User();
        user.setName("testuser");
        user.setUsername("testuser@example.com");
        user.setPassword("testpassword");
        user.setPhoneNumber("1234567890");
        user.setGender("Male");
        user.setAge(14);
        user.setAddress("testing");
        

        when(userRepo.save(user)).thenReturn(user);

        User createdUser = userServiceImpl.addUser(user);

        assertEquals(user.getName(), createdUser.getName());
        assertEquals(user.getUsername(), createdUser.getUsername());
        assertEquals(user.getPassword(), createdUser.getPassword());
        assertEquals(user.getAddress(), createdUser.getAddress());
        assertEquals(user.getPhoneNumber(), createdUser.getPhoneNumber());
        assertEquals(user.getGender(), createdUser.getGender());
        
        
    }
    
    //Test updating an existing user and validate the result
    @Test
    public void testUpdateUser() throws UserNotFoundException  {
        User existingUser = new User();
        existingUser.setName("existinguser");
        existingUser.setUsername("existinguser@example.com");
        existingUser.setPassword("existingpassword");
        existingUser.setPhoneNumber("9876543210");

        User updatedUser = new User();
        updatedUser.setName("updateduser");
        updatedUser.setUsername("updateduser@example.com");
        updatedUser.setPassword("updatedpassword");
        updatedUser.setPhoneNumber("73979589884");

        when(userRepo.findByUsername(existingUser.getUsername())).thenReturn(java.util.Optional.of(existingUser));
        when(userRepo.save(existingUser)).thenReturn(updatedUser);

        User result;
		
			result = userServiceImpl.updateUser(existingUser.getUsername(), updatedUser);
			if(result==null) {
				throw new UserNotFoundException("User is not valid");
			}
			assertEquals(updatedUser.getName(), result.getName());
		        assertEquals(updatedUser.getUsername(), result.getUsername());
		        assertEquals(updatedUser.getAddress(), result.getAddress());
		        assertEquals(updatedUser.getPassword(), result.getPassword());
		        assertEquals(updatedUser.getPhoneNumber(), result.getPhoneNumber());
		

       
    }

    // Test updating a non-existent user and expect a ResourceNotFoundException
    @Test
    public void testUpdateUserNotFound() {
        User updatedUser = new User();
        updatedUser.setUsername("xyz");

        when(userRepo.findByUsername(updatedUser.getUsername())).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.updateUser(updatedUser.getUsername(), updatedUser));
    }

    // Test listing all users and validate the result
    @Test
    public void testListAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());

        when(userRepo.findAll()).thenReturn(userList);

        List<User> result = userServiceImpl.getUsers();

        assertEquals(userList.size(), result.size());
    }

    // Test deleting a user and verify the delete operation
    @Test
    public void testDeleteUser() throws UserNotFoundException  {
        String username = "xyz";
        when(userRepo.findByUsername(username)).thenReturn(java.util.Optional.of(new User()));

       
			userServiceImpl.deleteUserByUsername(username);
			verify(userRepo, times(1)).deleteByUsername(username);
		
        
    }

    // Test deleting a non-existent user and expect a ResourceNotFoundException
    @Test
    public void testDeleteUserNotFound() {
        String username = "abc";
        when(userRepo.findByUsername(username)).thenReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userServiceImpl.deleteUserByUsername(username));
    }
    
    // Test getting a user by ID and validate the result
    @Test
    public void testGetUserByUsername() {
        String username = "testing";
        User user = new User();
        user.setUsername(username);

        when(userRepo.findByUsername(username)).thenReturn(java.util.Optional.of(user));

        User result = userServiceImpl.getUserByUsername(username).get();

        assertEquals(username, result.getUsername());
    }

    // Test getting a non-existent user by ID and expect a ResourceNotFoundException
//    @Testwwwwwwwwwwwwwwwwwwwwwww
//    public void testGetUserByUsernameNotFound() {
//    	String username = "trying";
//        when(userRepo.findByUsername(username)).thenReturn(java.util.Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByUsername(username));
//    }

}
