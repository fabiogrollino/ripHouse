package org.riphouse.dao.impl.jdbc.commons;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.riphouse.config.LoaderConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSourceProvider {

	private static Logger logger = LoggerFactory.getLogger(DataSourceProvider.class);
	private static String name = LoaderConfig.getConfig().getDataSourceName();

	private final static DataSource datasource = createDataSource() ;

	public static DataSource getDataSource() {
		return datasource;
	}



	private static DataSource createDataSource() {
		DataSource ds = null;
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup(name);
		} catch ( NamingException e) {
			logger.error(e.getMessage(), e);
			throw new ExceptionInInitializerError(e);
		}
		return ds;
	}


}
