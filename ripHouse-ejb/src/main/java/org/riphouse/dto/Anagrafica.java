/*
 * Created on 25 dic 2017 ( Time 21:05:47 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.riphouse.dto;

import java.io.Serializable;

import java.util.Date;

/**
 * Java bean for 'Anagrafica' entity
 * 
 * @author Telosys Tools
 *
 */
public class Anagrafica implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : Nome VARCHAR 
    private String nome;

    // DB : Cognome VARCHAR 
    private String cognome;

    // DB : CodiceFiscale VARCHAR 
    private String codicefiscale;

    // DB : DataNascita DATE 
    private Date datanascita;

    // DB : ComuneNascita VARCHAR 
    private String comunenascita;



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
    public void setNome( String nome ) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public void setCognome( String cognome ) {
        this.cognome = cognome;
    }
    public String getCognome() {
        return this.cognome;
    }

    public void setCodicefiscale( String codicefiscale ) {
        this.codicefiscale = codicefiscale;
    }
    public String getCodicefiscale() {
        return this.codicefiscale;
    }

    public void setDatanascita( Date datanascita ) {
        this.datanascita = datanascita;
    }
    public Date getDatanascita() {
        return this.datanascita;
    }

    public void setComunenascita( String comunenascita ) {
        this.comunenascita = comunenascita;
    }
    public String getComunenascita() {
        return this.comunenascita;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(nome);
        sb.append("|");
        sb.append(cognome);
        sb.append("|");
        sb.append(codicefiscale);
        sb.append("|");
        sb.append(datanascita);
        sb.append("|");
        sb.append(comunenascita);
        return sb.toString(); 
    } 


}
