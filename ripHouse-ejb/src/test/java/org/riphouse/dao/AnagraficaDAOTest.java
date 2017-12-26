package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Anagrafica;

public class AnagraficaDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE anagrafica ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Nome VARCHAR(100) NOT NULL,"
			+ "Cognome VARCHAR(100) NOT NULL,"
			+ "CodiceFiscale VARCHAR(16) ,"
			+ "DataNascita DATE ,"
			+ "ComuneNascita VARCHAR(100) ,"
			+ "PRIMARY KEY(id)"
			+ ");"
			;

	@BeforeClass
	public static void init() {
		DAOTestUtil.initDatabase(CREATE_TABLE) ;
	}

	@Test
	public void testDAO() throws SQLException {
    	System.out.println("test AnagraficaDAO ");
    	AnagraficaDAO dao = DAOProvider.getDAO(AnagraficaDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Anagrafica anagrafica = new Anagrafica();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		anagrafica.setNome("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Nome" : java.lang.String
		anagrafica.setCognome("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Cognome" : java.lang.String
		anagrafica.setCodicefiscale("AAAAAAAAAAAAAAAA"); // "CodiceFiscale" : java.lang.String
		anagrafica.setDatanascita(java.sql.Date.valueOf("2001-06-22")); // "DataNascita" : java.util.Date
		anagrafica.setComunenascita("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "ComuneNascita" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + anagrafica );
    	Long pkAutoIncr = dao.insert(anagrafica);
    	anagrafica.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(anagrafica) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Anagrafica anagrafica2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(anagrafica2);
    	Assert.assertTrue( dao.exists(anagrafica2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		anagrafica2.setNome("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Nome" : java.lang.String
		anagrafica2.setCognome("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Cognome" : java.lang.String
		anagrafica2.setCodicefiscale("BBBBBBBBBBBBBBBB"); // "CodiceFiscale" : java.lang.String
		anagrafica2.setDatanascita(java.sql.Date.valueOf("2002-06-22")); // "DataNascita" : java.util.Date
		anagrafica2.setComunenascita("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "ComuneNascita" : java.lang.String
    	System.out.println("Update : " + anagrafica2 );
    	Assert.assertTrue( dao.update(anagrafica2) == 1 );
    	
    	//--- LOAD
    	Anagrafica anagrafica3 = new Anagrafica();
    	anagrafica3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(anagrafica3) );
    	System.out.println("Loaded : " + anagrafica3 );
		Assert.assertEquals(anagrafica2.getNome(), anagrafica3.getNome() ); 
		Assert.assertEquals(anagrafica2.getCognome(), anagrafica3.getCognome() ); 
		Assert.assertEquals(anagrafica2.getCodicefiscale(), anagrafica3.getCodicefiscale() ); 
		Assert.assertEquals(anagrafica2.getDatanascita(), anagrafica3.getDatanascita() ); 
		Assert.assertEquals(anagrafica2.getComunenascita(), anagrafica3.getComunenascita() ); 


    	anagrafica3.setId(3000L);
    	Assert.assertFalse( dao.load(anagrafica3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + anagrafica2 );
    	Assert.assertTrue( dao.delete(anagrafica2) == 1 );
    	Assert.assertTrue( dao.delete(anagrafica2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(anagrafica2) ) ;
    	anagrafica2 = dao.find(1000L);
    	Assert.assertNull( anagrafica2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
