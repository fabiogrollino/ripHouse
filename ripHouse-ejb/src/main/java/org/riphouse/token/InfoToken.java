package org.riphouse.token;

import java.io.Serializable;

public class InfoToken implements Serializable {

	private static final long serialVersionUID = -5732790456377443183L;	
	
	private String user;
	private Integer level;
	private Long idUser;
	private Long idAnagrafica;
	
	public InfoToken() {
	}

	public InfoToken(String user, Integer level, Long idUser, Long idAnagrafica) {
		this.user = user;
		this.level = level;
		this.idUser = idUser;
		this.idAnagrafica = idAnagrafica;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
