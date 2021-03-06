package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Assistito;

public class AssistitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE assistito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "anagrafica BIGINT NOT NULL,"
			+ "dataScadenzaLibrettoSanitario DATE ,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test AssistitoDAO ");
    	AssistitoDAO dao = DAOProvider.getDAO(AssistitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Assistito assistito = new Assistito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		assistito.setAnagrafica(1000L); // "anagrafica" : java.lang.Long
		assistito.setDatascadenzalibrettosanitario(java.sql.Date.valueOf("2001-06-22")); // "dataScadenzaLibrettoSanitario" : java.util.Date

    	//--- INSERT
    	System.out.println("Insert : " + assistito );
    	Long pkAutoIncr = dao.insert(assistito);
    	assistito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(assistito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Assistito assistito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(assistito2);
    	Assert.assertTrue( dao.exists(assistito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		assistito2.setAnagrafica(2000L); // "anagrafica" : java.lang.Long
		assistito2.setDatascadenzalibrettosanitario(java.sql.Date.valueOf("2002-06-22")); // "dataScadenzaLibrettoSanitario" : java.util.Date
    	System.out.println("Update : " + assistito2 );
    	Assert.assertTrue( dao.update(assistito2) == 1 );
    	
    	//--- LOAD
    	Assistito assistito3 = new Assistito();
    	assistito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(assistito3) );
    	System.out.println("Loaded : " + assistito3 );
		Assert.assertEquals(assistito2.getAnagrafica(), assistito3.getAnagrafica() ); 
		Assert.assertEquals(assistito2.getDatascadenzalibrettosanitario(), assistito3.getDatascadenzalibrettosanitario() ); 


    	assistito3.setId(3000L);
    	Assert.assertFalse( dao.load(assistito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + assistito2 );
    	Assert.assertTrue( dao.delete(assistito2) == 1 );
    	Assert.assertTrue( dao.delete(assistito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(assistito2) ) ;
    	assistito2 = dao.find(1000L);
    	Assert.assertNull( assistito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
