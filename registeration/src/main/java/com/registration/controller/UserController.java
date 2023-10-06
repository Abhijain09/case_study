package com.registration.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.registration.exception.UserNotFoundException;
import com.registration.model.User;
import com.registration.services.UserServices;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserServices userServices;
	
//	get all user
	@GetMapping("/")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = userServices.getUsers();
        return ResponseEntity.ok(user);
    }
	
	
	 @GetMapping("/{username}")
	    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
	        Optional<User> user = userServices.getUserByUsername(username);
	        return user.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }
	
	
	 @PostMapping("/")
	    public ResponseEntity<User> addUser(@RequestBody User user) {
	        try {
	        	User createdUser = userServices.addUser(user);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        }
	    }
	 
	 
	 @PutMapping("/{username}")
	    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User updatedUser) throws UserNotFoundException {
	        try {
	        	User user = userServices.updateUser(username, updatedUser);
	            return ResponseEntity.ok(user);
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	        } catch (UserNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }

	    @DeleteMapping("/{username}")
	    public ResponseEntity<Void> deleteProductByUserName(@PathVariable String username) throws UserNotFoundException {
	        try {
	        	userServices.deleteUserByUsername(username);
	            return ResponseEntity.noContent().build();
	        } catch (UserNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 
	
	
	
	
	
	
	

	
	
	
	  

	

    
	
}
