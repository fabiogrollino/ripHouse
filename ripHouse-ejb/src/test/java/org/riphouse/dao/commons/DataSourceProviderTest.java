package org.riphouse.dao.commons;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.riphouse.dao.impl.jdbc.commons.DataSourceProvider;
import org.junit.Assert;
import org.junit.Test;

public class DataSourceProviderTest {

	@Test
	public void test1() throws SQLException {
    	System.out.println("test DataSourceProvider ");
    	
    	DataSource dataSource = DataSourceProvider.getDataSource();
    	System.out.println("DataSource ready. ");
    	
    	Connection c = dataSource.getConnection();
    	System.out.println("Connection ready. ");
    	
    	Assert.assertNotNull( c );
    	
    	c.close();
    	System.out.println("Connection closed. ");
	}
}
