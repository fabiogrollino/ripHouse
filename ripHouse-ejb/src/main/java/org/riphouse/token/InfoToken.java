package org.riphouse.token;

import java.io.Serializable;

public class InfoToken implements Serializable {

	private static final long serialVersionUID = -5732790456377443183L;	
	
	private Long idUtente;
	private String user;
	private Long idAnagrafica;
	
	//TODO inserire anche il livello di utenza
	
	public InfoToken() {
	}

	public InfoToken(Long idUtente, String user, Long anagrafica) {
		this.idUtente = idUtente;
		this.user = user;
		this.idAnagrafica = anagrafica;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Long idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	
	
}
