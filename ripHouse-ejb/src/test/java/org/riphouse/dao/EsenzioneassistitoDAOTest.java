package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Esenzioneassistito;

public class EsenzioneassistitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE esenzioneassisitito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Esenzione BIGINT ,"
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
    	System.out.println("test EsenzioneassisititoDAO ");
    	EsenzioneassistitoDAO dao = DAOProvider.getDAO(EsenzioneassistitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Esenzioneassistito esenzioneassisitito = new Esenzioneassistito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		esenzioneassisitito.setEsenzione(1000L); // "Esenzione" : java.lang.Long
		esenzioneassisitito.setAssistito(1000L); // "Assistito" : java.lang.Long

    	//--- INSERT
    	System.out.println("Insert : " + esenzioneassisitito );
    	Long pkAutoIncr = dao.insert(esenzioneassisitito);
    	esenzioneassisitito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(esenzioneassisitito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Esenzioneassistito esenzioneassisitito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(esenzioneassisitito2);
    	Assert.assertTrue( dao.exists(esenzioneassisitito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		esenzioneassisitito2.setEsenzione(2000L); // "Esenzione" : java.lang.Long
		esenzioneassisitito2.setAssistito(2000L); // "Assistito" : java.lang.Long
    	System.out.println("Update : " + esenzioneassisitito2 );
    	Assert.assertTrue( dao.update(esenzioneassisitito2) == 1 );
    	
    	//--- LOAD
    	Esenzioneassistito esenzioneassisitito3 = new Esenzioneassistito();
    	esenzioneassisitito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(esenzioneassisitito3) );
    	System.out.println("Loaded : " + esenzioneassisitito3 );
		Assert.assertEquals(esenzioneassisitito2.getEsenzione(), esenzioneassisitito3.getEsenzione() ); 
		Assert.assertEquals(esenzioneassisitito2.getAssistito(), esenzioneassisitito3.getAssistito() ); 


    	esenzioneassisitito3.setId(3000L);
    	Assert.assertFalse( dao.load(esenzioneassisitito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + esenzioneassisitito2 );
    	Assert.assertTrue( dao.delete(esenzioneassisitito2) == 1 );
    	Assert.assertTrue( dao.delete(esenzioneassisitito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(esenzioneassisitito2) ) ;
    	esenzioneassisitito2 = dao.find(1000L);
    	Assert.assertNull( esenzioneassisitito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
