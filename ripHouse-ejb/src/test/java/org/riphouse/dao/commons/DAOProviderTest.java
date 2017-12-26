package org.riphouse.dao.commons;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

public class DAOProviderTest {


	@Test
	public void test1() throws SQLException {
    	System.out.println("test DAOProvider ");

		//--- Test for each DAO interface
    	tryToGetDAO( org.riphouse.dao.AnagraficaDAO.class );
    	tryToGetDAO( org.riphouse.dao.AssistitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.CameraDAO.class );
    	tryToGetDAO( org.riphouse.dao.CaregiverassistitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.DizesenzioneDAO.class );
    	tryToGetDAO( org.riphouse.dao.DizintensitaDAO.class );
    	tryToGetDAO( org.riphouse.dao.DizpianoDAO.class );
    	tryToGetDAO( org.riphouse.dao.DiztipologiaassistenzialeDAO.class );
    	tryToGetDAO( org.riphouse.dao.DiztiporecapitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.EsenzioneassistitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.LettoDAO.class );
    	tryToGetDAO( org.riphouse.dao.LettoassistitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.MedicoassistitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.RecapitoDAO.class );
    	tryToGetDAO( org.riphouse.dao.RepartoDAO.class );
    	tryToGetDAO( org.riphouse.dao.ResidenzariposoDAO.class );
    	tryToGetDAO( org.riphouse.dao.UtenteDAO.class );
	}

	private <T> void tryToGetDAO(Class<T> interfaceClass) throws SQLException {
    	System.out.println("--- "  );
    	System.out.println("trying to get DAO for interface '" + interfaceClass.getCanonicalName() + "'...");

    	T dao = DAOProvider.getDAO(interfaceClass);
    	System.out.println("OK, DAO instance ready, class : '" + dao.getClass().getCanonicalName() + "'" );
    	
    	Assert.assertNotNull( dao );
    	Assert.assertTrue( interfaceClass.isAssignableFrom(dao.getClass()) );
	}
}
