package com.rahul.todo.jwt.resource;

import java.io.Serializable;
/*
 * Class to send the token string back to the client
 */
public class JwtTokenResponse implements Serializable {

	private static final long serialVersionUID = 8317676219297719109L;

	private final String token;

	public JwtTokenResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}