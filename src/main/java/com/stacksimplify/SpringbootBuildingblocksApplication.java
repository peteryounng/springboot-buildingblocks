package com.stacksimplify;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stacksimplify.entities.Order;
import com.stacksimplify.entities.User;
import com.stacksimplify.repository.OrderRepository;
import com.stacksimplify.repository.UserRepository;

@SpringBootApplication
public class SpringbootBuildingblocksApplication {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}
	
	@PostConstruct
	  private void initDb() {
	    User user1 = new User((long) 1, "kreddy", "Kalyan", "Reddy", "kreddy@stacksimplify.com", "admin", "ssn101");
	    User user2 = new User((long) 2, "gwiser",  "Greg", "Wiser", "gwiser@stacksimplify.com", "admin", "ssn102");
	    User user3 = new User((long) 3, "dmark", "David", "Mark", "dmark@stacksimplify.com", "admin", "ssn103");
	    
	    userRepository.save(user1);
	    userRepository.save(user2);
	    userRepository.save(user3);
	    
	    Order order1 = new Order((long)2001, "order11", user1);
	    Order order2 = new Order((long)2002, "order12", user1);
	    Order order3 = new Order((long)2003, "order13", user1);
	    Order order4 = new Order((long)2004, "order21", user2);
	    Order order5 = new Order((long)2005, "order22", user2);
	    Order order6 = new Order((long)2006, "order31", user3);
	    
	    orderRepository.save(order1);
	    orderRepository.save(order2);
	    orderRepository.save(order3);
	    orderRepository.save(order4);
	    orderRepository.save(order5);
	    orderRepository.save(order6);
	  }

}
