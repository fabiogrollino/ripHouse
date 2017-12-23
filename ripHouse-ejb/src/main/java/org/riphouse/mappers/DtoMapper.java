package org.riphouse.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;
import org.riphouse.dto.UtenteDTO;

public class DtoMapper {

	public static UtenteDTO createUtente(ResultSet rs) throws SQLException {
		UtenteDTO dto = new UtenteDTO();
		dto.setId(rs.getLong("id"));
		dto.setUser(rs.getString("user"));
		char[] password = rs.getString("password").toCharArray();
		dto.setPassword(ArrayUtils.toObject(password));
		dto.setAnagrafica(rs.getLong("Anagrafica"));
		return dto;
	}
}
