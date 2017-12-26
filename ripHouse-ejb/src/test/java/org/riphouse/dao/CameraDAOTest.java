package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Camera;

public class CameraDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE camera ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Repato BIGINT NOT NULL,"
			+ "NumeroPosti INTEGER NOT NULL,"
			+ "NumeroCamera INTEGER ,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test CameraDAO ");
    	CameraDAO dao = DAOProvider.getDAO(CameraDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Camera camera = new Camera();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		camera.setRepato(1000L); // "Repato" : java.lang.Long
		camera.setNumeroposti(100); // "NumeroPosti" : java.lang.Integer
		camera.setNumerocamera(100); // "NumeroCamera" : java.lang.Integer

    	//--- INSERT
    	System.out.println("Insert : " + camera );
    	Long pkAutoIncr = dao.insert(camera);
    	camera.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(camera) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Camera camera2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(camera2);
    	Assert.assertTrue( dao.exists(camera2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		camera2.setRepato(2000L); // "Repato" : java.lang.Long
		camera2.setNumeroposti(200); // "NumeroPosti" : java.lang.Integer
		camera2.setNumerocamera(200); // "NumeroCamera" : java.lang.Integer
    	System.out.println("Update : " + camera2 );
    	Assert.assertTrue( dao.update(camera2) == 1 );
    	
    	//--- LOAD
    	Camera camera3 = new Camera();
    	camera3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(camera3) );
    	System.out.println("Loaded : " + camera3 );
		Assert.assertEquals(camera2.getRepato(), camera3.getRepato() ); 
		Assert.assertEquals(camera2.getNumeroposti(), camera3.getNumeroposti() ); 
		Assert.assertEquals(camera2.getNumerocamera(), camera3.getNumerocamera() ); 


    	camera3.setId(3000L);
    	Assert.assertFalse( dao.load(camera3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + camera2 );
    	Assert.assertTrue( dao.delete(camera2) == 1 );
    	Assert.assertTrue( dao.delete(camera2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(camera2) ) ;
    	camera2 = dao.find(1000L);
    	Assert.assertNull( camera2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
