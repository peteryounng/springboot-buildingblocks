package com.stacksimplify.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.dto.UserMmDto;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.services.UserService;

@RestController
@RequestMapping("modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//Get user by id
		@GetMapping("/{id}")
		public UserMmDto getUserById(@PathVariable("id") @Min(1) Long id) {
			try {
				Optional<User> userOp = userService.getUserById(id);
				User user = userOp.get();
				
				UserMmDto userDto = modelMapper.map(user, UserMmDto.class);
				return userDto;
				
			} catch(UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
}
