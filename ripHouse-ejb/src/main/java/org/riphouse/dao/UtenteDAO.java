/*
 * Created on 25 dic 2017 ( Time 21:05:55 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.riphouse.dao;

import org.riphouse.dto.Utente;
import org.riphouse.exceptions.VechoException;

/**
 * Utente DAO interface
 * 
 * @author Telosys Tools
 */
public interface UtenteDAO {
	
	public Utente login(String username) throws VechoException;

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id
	 * @return the bean found or null if not found 
	 */
	public Utente find( Long id ) ;

	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param utente
	 * @return true if found, false if not found
	 */
	public boolean load( Utente utente ) ;
	
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param utente
	 * @return the generated value for the auto-incremented column
	 */
	public Long insert(Utente utente) ;

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param utente
	 * @return
	 */
	public int update(Utente utente) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id
	 * @return
	 */
	public int delete( Long id ) ;

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param utente
	 * @return
	 */
	public int delete( Utente utente ) ;

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id
	 * @return
	 */
	public boolean exists( Long id ) ;

	//----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param utente
	 * @return
	 */
	public boolean exists( Utente utente ) ;

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database table
	 * @return
	 */
	public long count() ;

}
