package org.riphouse.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -6397239022226901531L;

	private String username;
	private String password;
	
	public LoginRequest(
			@JsonProperty("username") String username, 
			@JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
	}

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
	
	
	
	
}
