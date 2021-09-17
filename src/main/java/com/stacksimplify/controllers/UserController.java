package com.stacksimplify.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserExistsException;
import com.stacksimplify.exceptions.UserNameNotFoundException;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.services.UserService;

@RestController
@Validated
@RequestMapping(value="users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Get all users
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	//Create user
	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			 userService.createUser(user);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch(UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	//Get user by id
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return userService.getUserById(id);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	//Update user
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user ) {
		try {
			return userService.updateUserById(id, user);
		} catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	//Delete user
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		userService.deleteUserById(id);
	}
	
	//Get user by username
	@GetMapping("/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException {
		User user = userService.getUserByUsername(username);
		if(user == null)
			throw new UserNameNotFoundException("Username: '"+username+"' not found in user repository");
		return user;
		
	}
	
}


