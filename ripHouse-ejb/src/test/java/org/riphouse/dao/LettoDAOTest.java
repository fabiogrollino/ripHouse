package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Letto;

public class LettoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE letto ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "NumeroLetto VARCHAR(100) NOT NULL,"
			+ "Camera BIGINT NOT NULL,"
			+ "Telefono VARCHAR(200) ,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test LettoDAO ");
    	LettoDAO dao = DAOProvider.getDAO(LettoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Letto letto = new Letto();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		letto.setNumeroletto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "NumeroLetto" : java.lang.String
		letto.setCamera(1000L); // "Camera" : java.lang.Long
		letto.setTelefono("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Telefono" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + letto );
    	Long pkAutoIncr = dao.insert(letto);
    	letto.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(letto) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Letto letto2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(letto2);
    	Assert.assertTrue( dao.exists(letto2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		letto2.setNumeroletto("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "NumeroLetto" : java.lang.String
		letto2.setCamera(2000L); // "Camera" : java.lang.Long
		letto2.setTelefono("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Telefono" : java.lang.String
    	System.out.println("Update : " + letto2 );
    	Assert.assertTrue( dao.update(letto2) == 1 );
    	
    	//--- LOAD
    	Letto letto3 = new Letto();
    	letto3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(letto3) );
    	System.out.println("Loaded : " + letto3 );
		Assert.assertEquals(letto2.getNumeroletto(), letto3.getNumeroletto() ); 
		Assert.assertEquals(letto2.getCamera(), letto3.getCamera() ); 
		Assert.assertEquals(letto2.getTelefono(), letto3.getTelefono() ); 


    	letto3.setId(3000L);
    	Assert.assertFalse( dao.load(letto3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + letto2 );
    	Assert.assertTrue( dao.delete(letto2) == 1 );
    	Assert.assertTrue( dao.delete(letto2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(letto2) ) ;
    	letto2 = dao.find(1000L);
    	Assert.assertNull( letto2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
