package org.riphouse.dao.commons;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.riphouse.dao.impl.jdbc.commons.DataSourceProvider;

public class DAOTestUtil {
	
    /**
     * Creates a table that will be used by the DAO tests 
     * @param createTableSQL
     */
    public static void initDatabase(String createTableSQL) { 
    	
    	System.out.println("===== initDatabase... ");
    	
    	//--- The datasource as defined in the 'jdbc properties' file  
    	DataSource datasource = DataSourceProvider.getDataSource();
    	
    	//--- Execute the CREATE TABLE SQL statement
    	try {
			createTable(createTableSQL, datasource);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error on 'create table'");
		}
    }
    
    private static void createTable(String createTableSQL, DataSource datasource) throws SQLException {
    	Connection conn = datasource.getConnection();
    	Statement stmt = conn.createStatement();
    	int r = stmt.executeUpdate(createTableSQL);
    	System.out.println("create table status : " + r);
    	stmt.close();
    	conn.close();
    }
}
