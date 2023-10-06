package com.registration.services;

import java.util.List;
import java.util.Optional;

import com.registration.exception.UserNotFoundException;
import com.registration.model.User;

public interface UserServices  {

	List<User> getUsers();

	

	User addUser(User user);

	

	
	Optional<User> getUserByUsername(String username);



	User updateUser(String username, User updatedUser) throws UserNotFoundException;



	void deleteUserByUsername(String username) throws UserNotFoundException;
	
	
	
}
