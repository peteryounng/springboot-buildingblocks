package com.stacksimplify.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.entities.Order;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.repository.OrderRepository;
import com.stacksimplify.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
public class UserHateoasController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderRepository orderRepository;

	// Get user by id
	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable("id") @Min(1) Long id) {

		try {
			// Getting user from Repo
			Optional<User> userOp = userService.getUserById(id);
			User user = userOp.get();
			Long userid = user.getId();
			// Create link for user
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(userid).withSelfRel();
			System.out.println("link: " + selflink.toString());
			user.add(selflink);
			// Return single user with link using EntityModel
			EntityModel<User> model = EntityModel.of(user);
			return model;

		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	// Get all users
	@GetMapping
	public CollectionModel<User> getAllUsers() throws UserNotFoundException {
		// Get all users from service
		List<User> users = userService.getAllUsers();
		// Iterate list of users
		for (User user : users) {
			// Create link for each user
			Link selflink = WebMvcLinkBuilder.linkTo(this.getClass()).slash(user.getId()).withSelfRel();
			user.add(selflink);

			// Relationship link with getallOrders
			CollectionModel<Order> orders = WebMvcLinkBuilder.methodOn(OrderHateoasController.class)
					.getAllOrders(user.getId());
			Link orderslink = WebMvcLinkBuilder.linkTo(orders).withRel("all-orders");
			user.add(orderslink);

		}
		// Return list of users using CollectionModel
		return CollectionModel.of(users);

	}

}
