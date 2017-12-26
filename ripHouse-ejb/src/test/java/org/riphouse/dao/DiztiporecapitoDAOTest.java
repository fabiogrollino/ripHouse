package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Diztiporecapito;

public class DiztiporecapitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE diztiporecapito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "TipoRecapito VARCHAR(200) NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test DiztiporecapitoDAO ");
    	DiztiporecapitoDAO dao = DAOProvider.getDAO(DiztiporecapitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Diztiporecapito diztiporecapito = new Diztiporecapito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		diztiporecapito.setTiporecapito("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "TipoRecapito" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + diztiporecapito );
    	Long pkAutoIncr = dao.insert(diztiporecapito);
    	diztiporecapito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(diztiporecapito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Diztiporecapito diztiporecapito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(diztiporecapito2);
    	Assert.assertTrue( dao.exists(diztiporecapito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		diztiporecapito2.setTiporecapito("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "TipoRecapito" : java.lang.String
    	System.out.println("Update : " + diztiporecapito2 );
    	Assert.assertTrue( dao.update(diztiporecapito2) == 1 );
    	
    	//--- LOAD
    	Diztiporecapito diztiporecapito3 = new Diztiporecapito();
    	diztiporecapito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(diztiporecapito3) );
    	System.out.println("Loaded : " + diztiporecapito3 );
		Assert.assertEquals(diztiporecapito2.getTiporecapito(), diztiporecapito3.getTiporecapito() ); 


    	diztiporecapito3.setId(3000L);
    	Assert.assertFalse( dao.load(diztiporecapito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + diztiporecapito2 );
    	Assert.assertTrue( dao.delete(diztiporecapito2) == 1 );
    	Assert.assertTrue( dao.delete(diztiporecapito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(diztiporecapito2) ) ;
    	diztiporecapito2 = dao.find(1000L);
    	Assert.assertNull( diztiporecapito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
