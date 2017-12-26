package org.riphouse.dao;

import java.sql.SQLException;
import java.util.Date;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Utente;

public class UtenteDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE utente ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "user VARCHAR(100) NOT NULL,"
			+ "password VARCHAR(100) NOT NULL,"
			+ "anagrafica BIGINT NOT NULL,"
			+ "livello INTEGER NOT NULL,"
			+ "ultimoLogin TIMESTAMP ,"
			+ "countLoginFailed INTEGER ,"
			+ "utenteBloccato TINYINT NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test UtenteDAO ");
    	UtenteDAO dao = DAOProvider.getDAO(UtenteDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Utente utente = new Utente();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		utente.setUser("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "user" : java.lang.String
		utente.setPassword("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "password" : java.lang.String
		utente.setAnagrafica(1000L); // "anagrafica" : java.lang.Long
		utente.setLivello(100); // "livello" : java.lang.Integer
		utente.setUltimologin(java.sql.Date.valueOf("2001-06-22")); // "ultimoLogin" : java.util.Date
		utente.setCountloginfailed(100); // "countLoginFailed" : java.lang.Integer
		utente.setUtentebloccato(false); // "utenteBloccato" : java.lang.Boolean
		utente.setFirstLoginFailed(new Date());

    	//--- INSERT
    	System.out.println("Insert : " + utente );
    	Long pkAutoIncr = dao.insert(utente);
    	utente.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(utente) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Utente utente2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(utente2);
    	Assert.assertTrue( dao.exists(utente2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		utente2.setUser("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "user" : java.lang.String
		utente2.setPassword("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "password" : java.lang.String
		utente2.setAnagrafica(2000L); // "anagrafica" : java.lang.Long
		utente2.setLivello(200); // "livello" : java.lang.Integer
		utente2.setUltimologin(java.sql.Date.valueOf("2002-06-22")); // "ultimoLogin" : java.util.Date
		utente2.setCountloginfailed(200); // "countLoginFailed" : java.lang.Integer
		utente2.setUtentebloccato(true); // "utenteBloccato" : java.lang.Boolean
		utente2.setFirstLoginFailed(new Date());
    	System.out.println("Update : " + utente2 );
    	Assert.assertTrue( dao.update(utente2) == 1 );
    	
    	//--- LOAD
    	Utente utente3 = new Utente();
    	utente3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(utente3) );
    	System.out.println("Loaded : " + utente3 );
		Assert.assertEquals(utente2.getUser(), utente3.getUser() ); 
		Assert.assertEquals(utente2.getPassword(), utente3.getPassword() ); 
		Assert.assertEquals(utente2.getAnagrafica(), utente3.getAnagrafica() ); 
		Assert.assertEquals(utente2.getLivello(), utente3.getLivello() ); 
		Assert.assertEquals(utente2.getUltimologin(), utente3.getUltimologin() ); 
		Assert.assertEquals(utente2.getCountloginfailed(), utente3.getCountloginfailed() ); 
		Assert.assertEquals(utente2.getUtentebloccato(), utente3.getUtentebloccato() ); 


    	utente3.setId(3000L);
    	Assert.assertFalse( dao.load(utente3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + utente2 );
    	Assert.assertTrue( dao.delete(utente2) == 1 );
    	Assert.assertTrue( dao.delete(utente2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(utente2) ) ;
    	utente2 = dao.find(1000L);
    	Assert.assertNull( utente2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
