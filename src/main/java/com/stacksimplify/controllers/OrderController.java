package com.stacksimplify.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.entities.Order;
import com.stacksimplify.entities.User;
import com.stacksimplify.exceptions.UserNotFoundException;
import com.stacksimplify.repository.OrderRepository;
import com.stacksimplify.repository.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders of a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		Optional<User> user = userRepository.findById(userid);
		
		if(!user.isPresent())
			throw new UserNotFoundException("User not found thrown from getAllOrders OrderController");
		
		return user.get().getOrder();
	}
	
	//Create order
	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(userid);
		if(!user.isPresent())
			throw new UserNotFoundException("User not found thrown from getAllOrders OrderController");

		order.setUser(user.get());
		orderRepository.save(order);
	}
	
	
	
}
