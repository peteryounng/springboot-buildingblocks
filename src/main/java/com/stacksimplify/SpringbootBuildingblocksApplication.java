package com.stacksimplify;

import javax.annotation.PostConstruct;

import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stacksimplify.entities.User;
import com.stacksimplify.repository.UserRepository;

@SpringBootApplication
public class SpringbootBuildingblocksApplication {

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
	}
	
	@PostConstruct
	  private void initDb() {
	    User user1 = new User((long) 101, "kreddy", "Kalyan", "Reddy", "kreddy@stacksimplify.com", "admin", "ssn101");
	    User user2 = new User((long) 102, "gwiser",  "Greg", "Wiser", "gwiser@stacksimplify.com", "admin", "ssn102");
	    User user3 = new User((long) 103, "dmark", "David", "Mark", "dmark@stacksimplify.com", "admin", "ssn103");
	    
	    userRepository.save(user1);
	    userRepository.save(user2);
	    userRepository.save(user3);
	  }

}
