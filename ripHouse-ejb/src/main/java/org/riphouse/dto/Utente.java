package org.riphouse.dto;

import java.io.Serializable;

import java.util.Date;

public class Utente implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : user VARCHAR 
    private String user;

    // DB : password VARCHAR 
    private String password;

    // DB : anagrafica BIGINT 
    private Long anagrafica;

    // DB : livello INT 
    private Integer livello;

    // DB : ultimoLogin DATETIME 
    private Date ultimologin;

    // DB : countLoginFailed INT 
    private Integer countloginfailed;

    // DB : utenteBloccato TINYINT 
    private Byte utentebloccato;
    
    private Date firstLoginFailed;
    

	//----------------------------------------------------------------------
    // GETTER & SETTER FOR THE KEY FIELD
    //----------------------------------------------------------------------  
    public void setId( Long id ) {
        this.id = id ;
    }

    public Long getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR FIELDS
    //----------------------------------------------------------------------
    public void setUser( String user ) {
        this.user = user;
    }
    public String getUser() {
        return this.user;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
    public String getPassword() {
        return this.password;
    }

    public void setAnagrafica( Long anagrafica ) {
        this.anagrafica = anagrafica;
    }
    public Long getAnagrafica() {
        return this.anagrafica;
    }

    public void setLivello( Integer livello ) {
        this.livello = livello;
    }
    public Integer getLivello() {
        return this.livello;
    }

    public void setUltimologin( Date ultimologin ) {
        this.ultimologin = ultimologin;
    }
    public Date getUltimologin() {
        return this.ultimologin;
    }

    public void setCountloginfailed( Integer countloginfailed ) {
        this.countloginfailed = countloginfailed;
    }
    public Integer getCountloginfailed() {
        return this.countloginfailed;
    }

    public void setUtentebloccato( Byte utentebloccato ) {
        this.utentebloccato = utentebloccato;
    }
    public Byte getUtentebloccato() {
        return this.utentebloccato;
    }
    
    public Date getFirstLoginFailed() {
		return firstLoginFailed;
	}

	public void setFirstLoginFailed(Date firstLoginFailed) {
		this.firstLoginFailed = firstLoginFailed;
	}


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(user);
        sb.append("|");
        sb.append(password);
        sb.append("|");
        sb.append(anagrafica);
        sb.append("|");
        sb.append(livello);
        sb.append("|");
        sb.append(ultimologin);
        sb.append("|");
        sb.append(countloginfailed);
        sb.append("|");
        sb.append(utentebloccato);
        return sb.toString(); 
    } 


}
