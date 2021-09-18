package com.stacksimplify.hello;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	// Simple method
	// URI - /helloworld
	// @RequestMapping(method = RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld")
	public String helloWorld() {
		return "Hello World with @GetMapping";
	}

	@GetMapping("helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Pedro", "Escobar", "Mexico");
	}

	//International Hello world with an specific lenguage
	@GetMapping("/hello-int")
	public String getMessagesInI18NFormat(@RequestHeader(name = "Accept-Language", required = false) String locale) {
		return messageSource.getMessage("label.hello", null, new Locale(locale));
	}
	
	//Pretier way of writting international h ello wolrd
	@GetMapping("/hello-int2")
	public String getMessagesInI18NFormat2() {
		return messageSource.getMessage("label.hello", null, LocaleContextHolder.getLocale());
	}

}
