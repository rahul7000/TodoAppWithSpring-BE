package com.rahul.todo.helloworld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world")
	public String getHelloWorld() {
		return "Hello World";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		return new HelloWorldBean("Hello World Bean");
	}

	@RequestMapping(method = RequestMethod.GET, path = "/hello-world/{name}")
	public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name) {
		//throw new RuntimeException("Something went wrong");
		return new HelloWorldBean("Hello World, " + name);
	}
}

