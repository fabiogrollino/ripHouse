package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Recapito;

public class RecapitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE recapito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "TipoRecapito BIGINT NOT NULL,"
			+ "ValoreRecapito VARCHAR(200) NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test RecapitoDAO ");
    	RecapitoDAO dao = DAOProvider.getDAO(RecapitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Recapito recapito = new Recapito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		recapito.setTiporecapito(1000L); // "TipoRecapito" : java.lang.Long
		recapito.setValorerecapito("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "ValoreRecapito" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + recapito );
    	Long pkAutoIncr = dao.insert(recapito);
    	recapito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(recapito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Recapito recapito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(recapito2);
    	Assert.assertTrue( dao.exists(recapito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		recapito2.setTiporecapito(2000L); // "TipoRecapito" : java.lang.Long
		recapito2.setValorerecapito("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "ValoreRecapito" : java.lang.String
    	System.out.println("Update : " + recapito2 );
    	Assert.assertTrue( dao.update(recapito2) == 1 );
    	
    	//--- LOAD
    	Recapito recapito3 = new Recapito();
    	recapito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(recapito3) );
    	System.out.println("Loaded : " + recapito3 );
		Assert.assertEquals(recapito2.getTiporecapito(), recapito3.getTiporecapito() ); 
		Assert.assertEquals(recapito2.getValorerecapito(), recapito3.getValorerecapito() ); 


    	recapito3.setId(3000L);
    	Assert.assertFalse( dao.load(recapito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + recapito2 );
    	Assert.assertTrue( dao.delete(recapito2) == 1 );
    	Assert.assertTrue( dao.delete(recapito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(recapito2) ) ;
    	recapito2 = dao.find(1000L);
    	Assert.assertNull( recapito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
