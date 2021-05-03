package com.rahul.todo.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthController {

	@RequestMapping(method=RequestMethod.GET, path="/basicauth")
	public AuthenticationBean authenticate() {
		
		return new AuthenticationBean("you are authenticated",true);		
	}
}
