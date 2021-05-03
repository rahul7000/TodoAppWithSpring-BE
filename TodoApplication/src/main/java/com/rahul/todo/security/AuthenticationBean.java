package com.rahul.todo.security;

public class AuthenticationBean {

	private String message;
	private boolean isAuthenticated;

	public AuthenticationBean() {

	}

	public AuthenticationBean(String message, boolean isAuthenticated) {
		super();
		this.message = message;
		this.isAuthenticated = isAuthenticated;
	}

	public String getMessage() {
		return message;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAuthenticated(boolean isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}

	@Override
	public String toString() {
		return "AuthenticationBean [message=" + message + ", isAuthenticated=" + isAuthenticated + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAuthenticated ? 1231 : 1237);
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationBean other = (AuthenticationBean) obj;
		if (isAuthenticated != other.isAuthenticated)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

}
