package org.riphouse.dao;

import java.sql.SQLException;

import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import org.riphouse.dto.Dizintensita;

public class DizintensitaDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE dizintensita ("
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
    	System.out.println("test DizintensitaDAO ");
    	DizintensitaDAO dao = DAOProvider.getDAO(DizintensitaDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Dizintensita dizintensita = new Dizintensita();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		dizintensita.setDescrizione("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"); // "Descrizione" : java.lang.String

    	//--- INSERT
    	System.out.println("Insert : " + dizintensita );
    	Long pkAutoIncr = dao.insert(dizintensita);
    	dizintensita.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(dizintensita) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Dizintensita dizintensita2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(dizintensita2);
    	Assert.assertTrue( dao.exists(dizintensita2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		dizintensita2.setDescrizione("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB"); // "Descrizione" : java.lang.String
    	System.out.println("Update : " + dizintensita2 );
    	Assert.assertTrue( dao.update(dizintensita2) == 1 );
    	
    	//--- LOAD
    	Dizintensita dizintensita3 = new Dizintensita();
    	dizintensita3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(dizintensita3) );
    	System.out.println("Loaded : " + dizintensita3 );
		Assert.assertEquals(dizintensita2.getDescrizione(), dizintensita3.getDescrizione() ); 


    	dizintensita3.setId(3000L);
    	Assert.assertFalse( dao.load(dizintensita3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + dizintensita2 );
    	Assert.assertTrue( dao.delete(dizintensita2) == 1 );
    	Assert.assertTrue( dao.delete(dizintensita2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(dizintensita2) ) ;
    	dizintensita2 = dao.find(1000L);
    	Assert.assertNull( dizintensita2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
