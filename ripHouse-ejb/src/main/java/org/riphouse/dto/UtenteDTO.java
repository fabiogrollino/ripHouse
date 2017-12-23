package org.riphouse.dto;

import java.io.Serializable;

public class UtenteDTO implements Serializable {

	private static final long serialVersionUID = -3031471021546953538L;
	
	private Long id;
	private String user;
	private Character[] password;
	private Long anagrafica;
	
	//TODO inserire il livello di utenza
	//TODO inserire il n di tentetivi falliti
	//TODO inserire la data del primo tentativo fallito
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public Character[] getPassword() {
		return password;
	}
	
	public void setPassword(Character[] password) {
		this.password = password;
	}
	
	public Long getAnagrafica() {
		return anagrafica;
	}
	
	public void setAnagrafica(Long anagrafica) {
		this.anagrafica = anagrafica;
	}
	
}
