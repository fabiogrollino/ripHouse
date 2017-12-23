package org.riphouse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.riphouse.dao.UserDao;
import org.riphouse.dto.UtenteDTO;
import org.riphouse.exceptions.VechoException;
import org.riphouse.mappers.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Local(UserDao.class)
@Stateless
public class UserDaoImpl implements UserDao {
	
	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private Connection conn;
	private String name = "java:jboss/datasources/vechoDS";

	@Override
	public UtenteDTO login(String username) throws VechoException {
		
		UtenteDTO utenteDTO = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Utenti WHERE user = ?");
		
		try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql.toString())) {
		
			int i = 1;
			st.setString(i++, username);
			
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					utenteDTO = DtoMapper.createUtente(rs);
				}
				if (rs.next()) {
					throw new VechoException("Unexpected results size!");
				}
				return utenteDTO;
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new VechoException(e);
		}
	}
	
	private Connection getConnection() throws VechoException {
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
