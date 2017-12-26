package org.riphouse.dto;

import java.io.Serializable;

public class Caregiverassistito implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : CareGiver BIGINT 
    private Long caregiver;

    // DB : Assistito BIGINT 
    private Long assistito;

    // DB : TipoCareGiver VARCHAR 
    private String tipocaregiver;



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
    public void setCaregiver( Long caregiver ) {
        this.caregiver = caregiver;
    }
    public Long getCaregiver() {
        return this.caregiver;
    }

    public void setAssistito( Long assistito ) {
        this.assistito = assistito;
    }
    public Long getAssistito() {
        return this.assistito;
    }

    public void setTipocaregiver( String tipocaregiver ) {
        this.tipocaregiver = tipocaregiver;
    }
    public String getTipocaregiver() {
        return this.tipocaregiver;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(caregiver);
        sb.append("|");
        sb.append(assistito);
        sb.append("|");
        sb.append(tipocaregiver);
        return sb.toString(); 
    } 


}
