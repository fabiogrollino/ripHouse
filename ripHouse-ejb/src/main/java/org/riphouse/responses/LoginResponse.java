package org.riphouse.responses;

import java.io.Serializable;

public class LoginResponse implements Serializable{

	private static final long serialVersionUID = -1312576477404827849L;
	
	private String token;
	private String user;
	private Integer level;
	private Long idUser;
	private Long idAnagrafica;

	public LoginResponse() {
	}

	public LoginResponse(String token, String user, Integer level, Long idUser, Long idAnagrafica) {
		this.token = token;
		this.user = user;
		this.level = level;
		this.idUser = idUser;
		this.idAnagrafica = idAnagrafica;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Long getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	
	

}
