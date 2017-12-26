package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Caregiverassistito;

public class CaregiverassistitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE caregiverassistito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "CareGiver BIGINT ,"
			+ "Assisitito BIGINT NOT NULL,"
			+ "TipoCareGiver VARCHAR(200) ,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test CaregiverassistitoDAO ");
    	CaregiverassistitoDAO dao = DAOProvider.getDAO(CaregiverassistitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Caregiverassistito caregiverassistito = new Caregiverassistito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		caregiverassistito.setCaregiver(1000L); // "CareGiver" : java.lang.Long
		caregiverassistito.setAssistito(1000L); // "Assisitito" : java.lang.Long
		caregiverassistito.setTipocaregiver("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "TipoCareGiver" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + caregiverassistito );
    	Long pkAutoIncr = dao.insert(caregiverassistito);
    	caregiverassistito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(caregiverassistito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Caregiverassistito caregiverassistito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(caregiverassistito2);
    	Assert.assertTrue( dao.exists(caregiverassistito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		caregiverassistito2.setCaregiver(2000L); // "CareGiver" : java.lang.Long
		caregiverassistito2.setAssistito(2000L); // "Assisitito" : java.lang.Long
		caregiverassistito2.setTipocaregiver("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "TipoCareGiver" : java.lang.String
    	System.out.println("Update : " + caregiverassistito2 );
    	Assert.assertTrue( dao.update(caregiverassistito2) == 1 );
    	
    	//--- LOAD
    	Caregiverassistito caregiverassistito3 = new Caregiverassistito();
    	caregiverassistito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(caregiverassistito3) );
    	System.out.println("Loaded : " + caregiverassistito3 );
		Assert.assertEquals(caregiverassistito2.getCaregiver(), caregiverassistito3.getCaregiver() ); 
		Assert.assertEquals(caregiverassistito2.getAssistito(), caregiverassistito3.getAssistito() ); 
		Assert.assertEquals(caregiverassistito2.getTipocaregiver(), caregiverassistito3.getTipocaregiver() ); 


    	caregiverassistito3.setId(3000L);
    	Assert.assertFalse( dao.load(caregiverassistito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + caregiverassistito2 );
    	Assert.assertTrue( dao.delete(caregiverassistito2) == 1 );
    	Assert.assertTrue( dao.delete(caregiverassistito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(caregiverassistito2) ) ;
    	caregiverassistito2 = dao.find(1000L);
    	Assert.assertNull( caregiverassistito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
