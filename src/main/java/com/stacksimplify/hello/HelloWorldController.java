package com.stacksimplify.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	//Simple method
	//URI - /helloworld
	//@RequestMapping(method = RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World with @GetMapping";
	}
	
	@GetMapping("helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Pedro", "Escobar", "Mexico");
	}
}
