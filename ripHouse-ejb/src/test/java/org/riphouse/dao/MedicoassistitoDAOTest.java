package org.riphouse.dao;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.riphouse.dao.commons.DAOProvider;
import org.riphouse.dao.commons.DAOTestUtil;
import org.riphouse.dto.Medicoassistito;

public class MedicoassistitoDAOTest {


	private static final String CREATE_TABLE = 
			 "CREATE TABLE medicoassistito ("
			+ "id IDENTITY AUTO_INCREMENT NOT NULL,"
			+ "Medico BIGINT ,"
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
    	System.out.println("test MedicoassistitoDAO ");
    	MedicoassistitoDAO dao = DAOProvider.getDAO(MedicoassistitoDAO.class);

    	Assert.assertTrue( dao.count() == 0 );

    	Medicoassistito medicoassistito = new Medicoassistito();
		//--- Key values
		// Auto-incremented key : nothing to set in the Primary Key
		//--- Other values
		medicoassistito.setMedico(1000L); // "Medico" : java.lang.Long
		medicoassistito.setAssistito(1000L); // "Assistito" : java.lang.Long

    	//--- INSERT
    	System.out.println("Insert : " + medicoassistito );
    	Long pkAutoIncr = dao.insert(medicoassistito);
    	medicoassistito.setId( pkAutoIncr );
    	Assert.assertTrue( dao.exists(pkAutoIncr) );
    	Assert.assertTrue( dao.count() == 1 );
    	Assert.assertTrue( dao.exists(medicoassistito) );
    	
    	//--- FIND
    	System.out.println("Find..." );
    	Medicoassistito medicoassistito2 = dao.find(pkAutoIncr);
    	Assert.assertNotNull(medicoassistito2);
    	Assert.assertTrue( dao.exists(medicoassistito2) ) ;
    	
    	//--- UPDATE
		//--- Change values
		medicoassistito2.setMedico(2000L); // "Medico" : java.lang.Long
		medicoassistito2.setAssistito(2000L); // "Assistito" : java.lang.Long
    	System.out.println("Update : " + medicoassistito2 );
    	Assert.assertTrue( dao.update(medicoassistito2) == 1 );
    	
    	//--- LOAD
    	Medicoassistito medicoassistito3 = new Medicoassistito();
    	medicoassistito3.setId( pkAutoIncr );
    	Assert.assertTrue( dao.load(medicoassistito3) );
    	System.out.println("Loaded : " + medicoassistito3 );
		Assert.assertEquals(medicoassistito2.getMedico(), medicoassistito3.getMedico() ); 
		Assert.assertEquals(medicoassistito2.getAssistito(), medicoassistito3.getAssistito() ); 


    	medicoassistito3.setId(3000L);
    	Assert.assertFalse( dao.load(medicoassistito3) );
    	
    	//--- DELETE
    	System.out.println("Delete : " + medicoassistito2 );
    	Assert.assertTrue( dao.delete(medicoassistito2) == 1 );
    	Assert.assertTrue( dao.delete(medicoassistito2) == 0 );
    	Assert.assertTrue( dao.delete(1000L) == 0 );

    	Assert.assertTrue( dao.count() == 0 );
    	Assert.assertFalse( dao.exists(1000L) ) ;
    	Assert.assertFalse( dao.exists(medicoassistito2) ) ;
    	medicoassistito2 = dao.find(1000L);
    	Assert.assertNull( medicoassistito2 );
    	
    	System.out.println("Normal end of DAO test." );
	}

}
