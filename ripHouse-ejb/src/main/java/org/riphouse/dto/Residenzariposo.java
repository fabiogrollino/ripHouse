/*
 * Created on 25 dic 2017 ( Time 21:05:54 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.riphouse.dto;

import java.io.Serializable;


/**
 * Java bean for 'Residenzariposo' entity
 * 
 * @author Telosys Tools
 *
 */
public class Residenzariposo implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : NomeResidenza VARCHAR 
    private String nomeresidenza;

    // DB : NumeroPosti INT 
    private Integer numeroposti;

    // DB : Indirizzo VARCHAR 
    private String indirizzo;

    // DB : Comune VARCHAR 
    private String comune;

    // DB : Cap VARCHAR 
    private String cap;



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
    public void setNomeresidenza( String nomeresidenza ) {
        this.nomeresidenza = nomeresidenza;
    }
    public String getNomeresidenza() {
        return this.nomeresidenza;
    }

    public void setNumeroposti( Integer numeroposti ) {
        this.numeroposti = numeroposti;
    }
    public Integer getNumeroposti() {
        return this.numeroposti;
    }

    public void setIndirizzo( String indirizzo ) {
        this.indirizzo = indirizzo;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }

    public void setComune( String comune ) {
        this.comune = comune;
    }
    public String getComune() {
        return this.comune;
    }

    public void setCap( String cap ) {
        this.cap = cap;
    }
    public String getCap() {
        return this.cap;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(nomeresidenza);
        sb.append("|");
        sb.append(numeroposti);
        sb.append("|");
        sb.append(indirizzo);
        sb.append("|");
        sb.append(comune);
        sb.append("|");
        sb.append(cap);
        return sb.toString(); 
    } 


}