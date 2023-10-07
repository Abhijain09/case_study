package com.registration.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.registration.exception.UserNotFoundException;
import com.registration.model.User;
import com.registration.repository.UserRepository;


@Service
public class UserServiceimpl implements UserServices {
	
	@Autowired
	private UserRepository csd;


	@Override
	public List<User> getUsers() {
		return csd.findAll();
	}

	@Override
	public User addUser(User cos) {
		
		return csd.save(cos);
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return csd.findByUsername(username);
	}

	@Override
	public User updateUser(String username, User updatedUser) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User existingUser = csd.findByUsername(username).orElseThrow(()->new UserNotFoundException("User Not found with "+ username));
        
		existingUser.setAddress(updatedUser.getAddress());
		existingUser.setAge(updatedUser.getAge());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setPassword(updatedUser.getPassword());
		existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
		existingUser.setName(updatedUser.getName());       
    	User updated = csd.save(existingUser);
    	return updatedUser;
	}

	@Override
	public void deleteUserByUsername(String username) throws UserNotFoundException {
		// TODO Auto-generated method stub
		 User existingProduct = csd.findByUsername(username).orElseThrow(()->new UserNotFoundException("User Not found with "+ username));
	        csd.delete(existingProduct);
	    }



}