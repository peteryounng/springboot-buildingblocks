package com.stacksimplify.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.entities.Order;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.repository.OrderRepository;
import com.stacksimplify.repository.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	// get all orders of a user
	@GetMapping("/{userid}/orders")
	public CollectionModel<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userid);

		if (!user.isPresent())
			throw new UserNotFoundException("User not found thrown from getAllOrders OrderController");

		List<Order> orders = user.get().getOrder();
		CollectionModel<Order> model = CollectionModel.of(orders);
		
		//Link link = WebMvcLinkBuilder.linkTo(this.getClass()).slash(object);
		
		return model;
	}

}
