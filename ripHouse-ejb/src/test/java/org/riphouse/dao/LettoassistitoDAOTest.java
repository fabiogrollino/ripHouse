package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Lettoassistito;

public class LettoassistitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE lettoassistito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Letto BIGINT ,"
			+ "Assisitito BIGINT NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test LettoassistitoDAO ");
    	LettoassistitoDAO dao = DAOProvider.getDAO(LettoassistitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Lettoassistito lettoassistito = new Lettoassistito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		lettoassistito.setLetto(1000L); // "Letto" : java.lang.Long
		lettoassistito.setAssistito(1000L); // "Assistito" : java.lang.Long

    	//--- INSERT
    	System.out.println("Insert : " + lettoassistito );
    	Long pkAutoIncr = dao.insert(lettoassistito);
    	lettoassistito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(lettoassistito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Lettoassistito lettoassistito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(lettoassistito2);
    	Assert.assertTrue( dao.exists(lettoassistito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		lettoassistito2.setLetto(2000L); // "Letto" : java.lang.Long
		lettoassistito2.setAssistito(2000L); // "Assistito" : java.lang.Long
    	System.out.println("Update : " + lettoassistito2 );
    	Assert.assertTrue( dao.update(lettoassistito2) == 1 );
    	
    	//--- LOAD
    	Lettoassistito lettoassistito3 = new Lettoassistito();
    	lettoassistito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(lettoassistito3) );
    	System.out.println("Loaded : " + lettoassistito3 );
		Assert.assertEquals(lettoassistito2.getLetto(), lettoassistito3.getLetto() ); 
		Assert.assertEquals(lettoassistito2.getAssistito(), lettoassistito3.getAssistito() ); 


    	lettoassistito3.setId(3000L);
    	Assert.assertFalse( dao.load(lettoassistito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + lettoassistito2 );
    	Assert.assertTrue( dao.delete(lettoassistito2) == 1 );
    	Assert.assertTrue( dao.delete(lettoassistito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(lettoassistito2) ) ;
    	lettoassistito2 = dao.find(1000L);
    	Assert.assertNull( lettoassistito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
