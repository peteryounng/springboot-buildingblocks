package com.stacksimplify.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserExistsException;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException{
		User existingUser = userRepository.findByUsername(user.getUsername());
		//If user exists by username, throw exception
		if(existingUser != null) {
			throw new UserExistsException("User name already exists");
		}
		return userRepository.save(user);
	}
	
	//Get user by id
	public Optional<User> getUserById(Long id) throws UserNotFoundException {

		System.out.println("ID to find: "+ id.intValue());
		Optional<User> user = userRepository.findById(id);
		//Check if user is present
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not found in database");
		} 		
		return user;
	}
	
	//Update user by id
	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		//Check if user is present
		if(!optionalUser.isPresent()) {
			System.out.println("User not present");
			throw new UserNotFoundException("User not found in database, provide the correct Id");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	public void deleteUserById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found in Repository");
		}
	}
	
	public User getUserByUsername(String username){
		return userRepository.findByUsername(username);
	}
	
}
