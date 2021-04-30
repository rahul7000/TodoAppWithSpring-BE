package com.rahul.todo.helloworld;
public class HelloWorldBean {
	String message;

	public HelloWorldBean() {

	}

	public HelloWorldBean(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
