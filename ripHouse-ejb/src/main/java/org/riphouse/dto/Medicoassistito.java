/*
 * Created on 25 dic 2017 ( Time 21:05:53 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.riphouse.dto;

import java.io.Serializable;


/**
 * Java bean for 'Medicoassistito' entity
 * 
 * @author Telosys Tools
 *
 */
public class Medicoassistito implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : Medico BIGINT 
    private Long medico;

    // DB : Assisitito BIGINT 
    private Long assisitito;



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
    public void setMedico( Long medico ) {
        this.medico = medico;
    }
    public Long getMedico() {
        return this.medico;
    }

    public void setAssisitito( Long assisitito ) {
        this.assisitito = assisitito;
    }
    public Long getAssisitito() {
        return this.assisitito;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(medico);
        sb.append("|");
        sb.append(assisitito);
        return sb.toString(); 
    } 


}