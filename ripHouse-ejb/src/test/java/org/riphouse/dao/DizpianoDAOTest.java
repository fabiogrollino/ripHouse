package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Dizpiano;

public class DizpianoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE dizpiano ("
			+ "id BIGINT NOT NULL,"
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
    	System.out.println("test DizpianoDAO ");
    	DizpianoDAO dao = DAOProvider.getDAO(DizpianoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Dizpiano dizpiano = new Dizpiano();
		//--- Key values
		dizpiano.setId(1000L); // "id" : java.lang.Long
		//--- Other values
		dizpiano.setDescrizione("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Descrizione" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + dizpiano );
    	dao.insert(dizpiano);
    	Assert.assertTrue( dao.exists(1000L) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(dizpiano) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Dizpiano dizpiano2 = dao.find(1000L);
    	Assert.assertNotNull(dizpiano2);
		Assert.assertTrue( dizpiano2.getId() == 1000L  ) ;
    	Assert.assertTrue( dao.exists(dizpiano2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		dizpiano2.setDescrizione("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Descrizione" : java.lang.String
    	System.out.println("Update : " + dizpiano2 );
    	Assert.assertTrue( dao.update(dizpiano2) == 1 );
    	
    	//--- LOAD
    	Dizpiano dizpiano3 = new Dizpiano();
    	dizpiano3.setId(1000L);
    	Assert.assertTrue( dao.load(dizpiano3) );
    	System.out.println("Loaded : " + dizpiano3 );
		Assert.assertEquals(dizpiano2.getDescrizione(), dizpiano3.getDescrizione() ); 


    	dizpiano3.setId(3000L);
    	Assert.assertFalse( dao.load(dizpiano3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + dizpiano2 );
    	Assert.assertTrue( dao.delete(dizpiano2) == 1 );
    	Assert.assertTrue( dao.delete(dizpiano2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(dizpiano2) ) ;
    	dizpiano2 = dao.find(1000L);
    	Assert.assertNull( dizpiano2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
