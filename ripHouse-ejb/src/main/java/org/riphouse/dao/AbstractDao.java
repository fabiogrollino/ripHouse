package org.riphouse.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.riphouse.exceptions.VechoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDao {

	private Logger logger = LoggerFactory.getLogger(AbstractDao.class);

	private Connection conn;
	private String name = "java:jboss/datasources/vechoDS";

	protected Connection getConnection() throws VechoException {
		try {
			if (conn == null || conn.isClosed()) {
				Context context = new InitialContext();
				DataSource ds = (DataSource) context.lookup(name);
				conn = ds.getConnection();
			}
		} catch (SQLException | NamingException e) {
			logger.error(e.getMessage(), e);
			throw new VechoException(e);
		}
		return conn;
	}
	
}
