package org.riphouse.dto;

import java.io.Serializable;

public class Esenzioneassistito implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
    //----------------------------------------------------------------------
    // DB : id BIGINT 
    private Long id;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    // DB : Esenzione BIGINT 
    private Long esenzione;

    // DB : Assistito BIGINT 
    private Long assistito;



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
    public void setEsenzione( Long esenzione ) {
        this.esenzione = esenzione;
    }
    public Long getEsenzione() {
        return this.esenzione;
    }

    public void setAssistito( Long assistito ) {
        this.assistito = assistito;
    }
    public Long getAssistito() {
        return this.assistito;
    }


    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(esenzione);
        sb.append("|");
        sb.append(assistito);
        return sb.toString(); 
    } 


}
