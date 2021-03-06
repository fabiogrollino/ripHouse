package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Reparto;

public class RepartoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE reparto ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "NomeReparto VARCHAR(200) NOT NULL,"
			+ "Piano BIGINT NOT NULL,"
			+ "Residenza BIGINT NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test RepartoDAO ");
    	RepartoDAO dao = DAOProvider.getDAO(RepartoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Reparto reparto = new Reparto();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		reparto.setNomereparto("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "NomeReparto" : java.lang.String
		reparto.setPiano(1000L); // "Piano" : java.lang.Long
		reparto.setResidenza(1000L); // "Residenza" : java.lang.Long

    	//--- INSERT
    	System.out.println("Insert : " + reparto );
    	Long pkAutoIncr = dao.insert(reparto);
    	reparto.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(reparto) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Reparto reparto2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(reparto2);
    	Assert.assertTrue( dao.exists(reparto2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		reparto2.setNomereparto("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "NomeReparto" : java.lang.String
		reparto2.setPiano(2000L); // "Piano" : java.lang.Long
		reparto2.setResidenza(2000L); // "Residenza" : java.lang.Long
    	System.out.println("Update : " + reparto2 );
    	Assert.assertTrue( dao.update(reparto2) == 1 );
    	
    	//--- LOAD
    	Reparto reparto3 = new Reparto();
    	reparto3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(reparto3) );
    	System.out.println("Loaded : " + reparto3 );
		Assert.assertEquals(reparto2.getNomereparto(), reparto3.getNomereparto() ); 
		Assert.assertEquals(reparto2.getPiano(), reparto3.getPiano() ); 
		Assert.assertEquals(reparto2.getResidenza(), reparto3.getResidenza() ); 


    	reparto3.setId(3000L);
    	Assert.assertFalse( dao.load(reparto3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + reparto2 );
    	Assert.assertTrue( dao.delete(reparto2) == 1 );
    	Assert.assertTrue( dao.delete(reparto2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(reparto2) ) ;
    	reparto2 = dao.find(1000L);
    	Assert.assertNull( reparto2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
