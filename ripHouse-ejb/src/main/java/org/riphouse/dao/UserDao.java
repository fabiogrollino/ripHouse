package org.riphouse.dao;

import org.riphouse.dto.UtenteDTO;
import org.riphouse.exceptions.VechoException;

public interface UserDao {

	public UtenteDTO login(String username) throws VechoException;
	
	public void update(UtenteDTO utenteDTO) throws VechoException; 
}
