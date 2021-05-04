package com.rahul.todo.jwt.resource;
/*
 * Runtime exception class for JWT auth related exceptions
 */
public class AuthenticationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
