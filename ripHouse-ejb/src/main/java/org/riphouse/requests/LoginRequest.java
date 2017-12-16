package org.riphouse.requests;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest implements Serializable {

	private static final long serialVersionUID = -6397239022226901531L;

	private String username;
	private Character[] password;
	
	public LoginRequest(
			@JsonProperty("username") String username, 
			@JsonProperty("password") Character[] password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Character[] getPassword() {
		return password;
	}

	public void setPassword(Character[] password) {
		this.password = password;
	}
	
	
	
	
}
