package org.riphouse.responses;

import java.io.Serializable;

public class LoginResponse implements Serializable{

	private static final long serialVersionUID = -1312576477404827849L;
	
	private String token;
	//TODO aggiungere altro se necessario

	public LoginResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

}
