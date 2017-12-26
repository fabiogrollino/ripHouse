package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Diztipologiaassistenziale;

public class DiztipologiaassistenzialeDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE diztipologiaassistenziale ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Descrizione VARCHAR(200) NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test DiztipologiaassistenzialeDAO ");
    	DiztipologiaassistenzialeDAO dao = DAOProvider.getDAO(DiztipologiaassistenzialeDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Diztipologiaassistenziale diztipologiaassistenziale = new Diztipologiaassistenziale();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		diztipologiaassistenziale.setDescrizione("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Descrizione" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + diztipologiaassistenziale );
    	Long pkAutoIncr = dao.insert(diztipologiaassistenziale);
    	diztipologiaassistenziale.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(diztipologiaassistenziale) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Diztipologiaassistenziale diztipologiaassistenziale2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(diztipologiaassistenziale2);
    	Assert.assertTrue( dao.exists(diztipologiaassistenziale2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		diztipologiaassistenziale2.setDescrizione("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Descrizione" : java.lang.String
    	System.out.println("Update : " + diztipologiaassistenziale2 );
    	Assert.assertTrue( dao.update(diztipologiaassistenziale2) == 1 );
    	
    	//--- LOAD
    	Diztipologiaassistenziale diztipologiaassistenziale3 = new Diztipologiaassistenziale();
    	diztipologiaassistenziale3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(diztipologiaassistenziale3) );
    	System.out.println("Loaded : " + diztipologiaassistenziale3 );
		Assert.assertEquals(diztipologiaassistenziale2.getDescrizione(), diztipologiaassistenziale3.getDescrizione() ); 


    	diztipologiaassistenziale3.setId(3000L);
    	Assert.assertFalse( dao.load(diztipologiaassistenziale3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + diztipologiaassistenziale2 );
    	Assert.assertTrue( dao.delete(diztipologiaassistenziale2) == 1 );
    	Assert.assertTrue( dao.delete(diztipologiaassistenziale2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(diztipologiaassistenziale2) ) ;
    	diztipologiaassistenziale2 = dao.find(1000L);
    	Assert.assertNull( diztipologiaassistenziale2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
