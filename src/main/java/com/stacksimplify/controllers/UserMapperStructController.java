package com.stacksimplify.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.dto.UserMsDto;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.mappers.UserMapper;
import com.stacksimplify.services.UserService;

@RestController
@RequestMapping(value = "mapstruct/users")
public class UserMapperStructController {

	@Autowired
	private UserService userService;
	
	//@Autowired
	private UserMapper userMapper = Mappers.getMapper( UserMapper.class );
	
	//Get all users
	@GetMapping
	public List<UserMsDto> getAllUsers(){
		return userMapper.usersToUserDtos(userService.getAllUsers());
	}
	
	// Get user by id
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable("id") @Min(1) Long id) {
		try {

			Optional<User> userOp = userService.getUserById(id);
			User user = userOp.get();

			UserMsDto userDto = userMapper.userToUserMsDto(user);
			return userDto;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
}
