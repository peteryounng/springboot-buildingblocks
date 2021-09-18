package com.stacksimplify.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.services.UserService;

@RestController
@RequestMapping(value = "/jacksonfilter/users")
@Validated
public class UserMappingJacksonController {

	@Autowired
	private UserService userService;

	// Get user by id --Filter fields with hashset
	@GetMapping("/{id}")
	public MappingJacksonValue getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			Optional<User> userOp = userService.getUserById(id);
			User user = userOp.get();

			Set<String> fields = new HashSet<String>();
			fields.add("id");
			fields.add("username");
			fields.add("ssn");
			fields.add("order");

			FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
					SimpleBeanPropertyFilter.filterOutAllExcept(fields));

			MappingJacksonValue mapper = new MappingJacksonValue(user);
			mapper.setFilters(filters);
			return mapper;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	// Get user by id --Filter fields with @RequestParam
	@GetMapping("/params/{id}")
		public MappingJacksonValue getUserById2(@PathVariable("id") @Min(1) Long id, 
				@RequestParam Set<String> fields) {

			try {
				Optional<User> userOp = userService.getUserById(id);
				User user = userOp.get();

				FilterProvider filters = new SimpleFilterProvider().addFilter("userFilter",
						SimpleBeanPropertyFilter.filterOutAllExcept(fields));

				MappingJacksonValue mapper = new MappingJacksonValue(user);
				mapper.setFilters(filters);
				return mapper;

			} catch (UserNotFoundException ex) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
			}
		}
	
	
}
