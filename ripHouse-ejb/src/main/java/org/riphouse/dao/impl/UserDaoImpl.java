package org.riphouse.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dao.AbstractDao;
import org.riphouse.dao.UserDao;
import org.riphouse.dto.UtenteDTO;
import org.riphouse.exceptions.VechoException;
import org.riphouse.mappers.DtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Local(UserDao.class)
@Stateless
public class UserDaoImpl extends AbstractDao implements UserDao {

	private Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@Override
	public UtenteDTO login(String username) throws VechoException {

		UtenteDTO utenteDTO = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM utente WHERE user = ?");

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

	@Override
	public void update(UtenteDTO utenteDTO) throws VechoException {
		// TODO Auto-generated method stub
	}

}
