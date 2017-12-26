package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Dizesenzione;

public class DizesenzioneDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE dizesenzione ("
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
    	System.out.println("test DizesenzioneDAO ");
    	DizesenzioneDAO dao = DAOProvider.getDAO(DizesenzioneDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Dizesenzione dizesenzione = new Dizesenzione();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		dizesenzione.setDescrizione("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Descrizione" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + dizesenzione );
    	Long pkAutoIncr = dao.insert(dizesenzione);
    	dizesenzione.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(dizesenzione) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Dizesenzione dizesenzione2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(dizesenzione2);
    	Assert.assertTrue( dao.exists(dizesenzione2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		dizesenzione2.setDescrizione("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Descrizione" : java.lang.String
    	System.out.println("Update : " + dizesenzione2 );
    	Assert.assertTrue( dao.update(dizesenzione2) == 1 );
    	
    	//--- LOAD
    	Dizesenzione dizesenzione3 = new Dizesenzione();
    	dizesenzione3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(dizesenzione3) );
    	System.out.println("Loaded : " + dizesenzione3 );
		Assert.assertEquals(dizesenzione2.getDescrizione(), dizesenzione3.getDescrizione() ); 


    	dizesenzione3.setId(3000L);
    	Assert.assertFalse( dao.load(dizesenzione3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + dizesenzione2 );
    	Assert.assertTrue( dao.delete(dizesenzione2) == 1 );
    	Assert.assertTrue( dao.delete(dizesenzione2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(dizesenzione2) ) ;
    	dizesenzione2 = dao.find(1000L);
    	Assert.assertNull( dizesenzione2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
