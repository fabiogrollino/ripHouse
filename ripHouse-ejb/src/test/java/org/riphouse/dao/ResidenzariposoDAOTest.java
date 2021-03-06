package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Residenzariposo;

public class ResidenzariposoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE residenzariposo ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "NomeResidenza VARCHAR(300) NOT NULL,"
			+ "NumeroPosti INTEGER NOT NULL,"
			+ "Indirizzo VARCHAR(300) NOT NULL,"
			+ "Comune VARCHAR(100) NOT NULL,"
			+ "Cap VARCHAR(6) NOT NULL,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test ResidenzariposoDAO ");
    	ResidenzariposoDAO dao = DAOProvider.getDAO(ResidenzariposoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Residenzariposo residenzariposo = new Residenzariposo();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		residenzariposo.setNomeresidenza("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "NomeResidenza" : java.lang.String
		residenzariposo.setNumeroposti(100); // "NumeroPosti" : java.lang.Integer
		residenzariposo.setIndirizzo("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Indirizzo" : java.lang.String
		residenzariposo.setComune("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Comune" : java.lang.String
		residenzariposo.setCap("AAAAAA"); // "Cap" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + residenzariposo );
    	Long pkAutoIncr = dao.insert(residenzariposo);
    	residenzariposo.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(residenzariposo) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Residenzariposo residenzariposo2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(residenzariposo2);
    	Assert.assertTrue( dao.exists(residenzariposo2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		residenzariposo2.setNomeresidenza("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "NomeResidenza" : java.lang.String
		residenzariposo2.setNumeroposti(200); // "NumeroPosti" : java.lang.Integer
		residenzariposo2.setIndirizzo("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Indirizzo" : java.lang.String
		residenzariposo2.setComune("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Comune" : java.lang.String
		residenzariposo2.setCap("BBBBBB"); // "Cap" : java.lang.String
    	System.out.println("Update : " + residenzariposo2 );
    	Assert.assertTrue( dao.update(residenzariposo2) == 1 );
    	
    	//--- LOAD
    	Residenzariposo residenzariposo3 = new Residenzariposo();
    	residenzariposo3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(residenzariposo3) );
    	System.out.println("Loaded : " + residenzariposo3 );
		Assert.assertEquals(residenzariposo2.getNomeresidenza(), residenzariposo3.getNomeresidenza() ); 
		Assert.assertEquals(residenzariposo2.getNumeroposti(), residenzariposo3.getNumeroposti() ); 
		Assert.assertEquals(residenzariposo2.getIndirizzo(), residenzariposo3.getIndirizzo() ); 
		Assert.assertEquals(residenzariposo2.getComune(), residenzariposo3.getComune() ); 
		Assert.assertEquals(residenzariposo2.getCap(), residenzariposo3.getCap() ); 


    	residenzariposo3.setId(3000L);
    	Assert.assertFalse( dao.load(residenzariposo3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + residenzariposo2 );
    	Assert.assertTrue( dao.delete(residenzariposo2) == 1 );
    	Assert.assertTrue( dao.delete(residenzariposo2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(residenzariposo2) ) ;
    	residenzariposo2 = dao.find(1000L);
    	Assert.assertNull( residenzariposo2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
