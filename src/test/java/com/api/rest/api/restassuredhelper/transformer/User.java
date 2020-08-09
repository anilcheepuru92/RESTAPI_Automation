package com.api.rest.api.restassuredhelper.transformer;

public class User {
	private String username;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String password;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString()
	{
		return String.format("Username = %s Password = %s", this.username, this.password);
	}
}
