package org.riphouse.dao.impl.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dao.UtenteDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;
import org.riphouse.dto.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Local(UtenteDAO.class)
@Stateless
public class UtenteDAOImpl extends GenericDAO<Utente> implements UtenteDAO {

	private Logger logger = LoggerFactory.getLogger(UtenteDAOImpl.class);

	private final static String SQL_SELECT = 
		"select id, user, password, anagrafica, livello, ultimoLogin, countLoginFailed, utenteBloccato, firstLoginFailed from utente where id = ?";

	private final static String SQL_INSERT = 
		"insert into utente ( user, password, anagrafica, livello, ultimoLogin, countLoginFailed, utenteBloccato, firstLoginFailed ) values ( ?, ?, ?, ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update utente set user = ?, password = ?, anagrafica = ?, livello = ?, ultimoLogin = ?, countLoginFailed = ?, utenteBloccato = ?, firstLoginFailed = ? where id = ?";

	private final static String SQL_DELETE = "delete from utente where id = ?";

	private final static String SQL_COUNT_ALL = "select count(*) from utente";

	private final static String SQL_COUNT = "select count(*) from utente where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public UtenteDAOImpl() {
		super();
	}
	

	@Override
	public Utente login(String username) {

		Utente utente = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Utente WHERE user = ?");
		try (Connection conn = getConnection(); PreparedStatement st = conn.prepareStatement(sql.toString())) {
			int i = 1;
			st.setString(i++, username);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					utente = new Utente();
					populateBean(rs, utente);
				}
				if (rs.next()) {
					throw new RuntimeException("Unexpected results size!");
				}
				return utente;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
	

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Utente newInstanceWithPrimaryKey( Long id ) {
		Utente utente = new Utente();
		utente.setId( id );
		return utente ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Utente find( Long id ) {
		Utente utente = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(utente) ) {
			return utente ;
		}
		else {
			return null ; // Not found
		}
	}
	//----------------------------------------------------------------------
	/**
	 * Loads the given bean, it is supposed to contains the primary key value(s) in its attribute(s)<br>
	 * If found, the given instance is populated with the values retrieved from the database<br>
	 * If not found, the given instance remains unchanged
	 * @param utente
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Utente utente ) {
		return super.doSelect(utente) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param utente
	 */
	@Override
	public Long insert(Utente utente) {
		Long key = super.doInsertAutoIncr(utente);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param utente
	 * @return
	 */
	@Override
	public int update(Utente utente) {
		return super.doUpdate(utente);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Utente utente = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(utente);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param utente
	 * @return
	 */
	@Override
	public int delete( Utente utente ) {
		return super.doDelete(utente);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Utente utente = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(utente);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param utente
	 * @return
	 */
	@Override
	public boolean exists( Utente utente ) {
		return super.doExists(utente);
	}

    //----------------------------------------------------------------------
	/**
	 * Counts all the records present in the database
	 * @return
	 */
	@Override
	public long count() {
		return super.doCountAll();
	}

    //----------------------------------------------------------------------
	@Override
	protected String getSqlSelect() {
		return SQL_SELECT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlInsert() {
		return SQL_INSERT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlUpdate() {
		return SQL_UPDATE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlDelete() {
		return SQL_DELETE ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCount() {
		return SQL_COUNT ;
	}
    //----------------------------------------------------------------------
	@Override
	protected String getSqlCountAll() {
		return SQL_COUNT_ALL ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Utente utente) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, utente.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Utente populateBean(ResultSet rs, Utente utente) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		utente.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { utente.setId(null); }; // not primitive number => keep null value if any
		utente.setUser(rs.getString("user")); // java.lang.String
		utente.setPassword(rs.getString("password")); // java.lang.String
		utente.setAnagrafica(rs.getLong("anagrafica")); // java.lang.Long
		if ( rs.wasNull() ) { utente.setAnagrafica(null); }; // not primitive number => keep null value if any
		utente.setLivello(rs.getInt("livello")); // java.lang.Integer
		if ( rs.wasNull() ) { utente.setLivello(null); }; // not primitive number => keep null value if any
		utente.setUltimologin(rs.getDate("ultimoLogin")); // java.util.Date
		utente.setCountloginfailed(rs.getInt("countLoginFailed")); // java.lang.Integer
		if ( rs.wasNull() ) { utente.setCountloginfailed(null); }; // not primitive number => keep null value if any
		utente.setUtentebloccato(rs.getBoolean("utenteBloccato")); // java.lang.Byte
		if ( rs.wasNull() ) { utente.setUtentebloccato(null); }; // not primitive number => keep null value if any
		utente.setFirstLoginFailed(rs.getDate("firstLoginFailed")); // java.lang.Date
		if ( rs.wasNull() ) { utente.setFirstLoginFailed(null); }; // not primitive number => keep null value if any
		return utente ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Utente utente) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, utente.getUser() ) ; // "user" : java.lang.String
		setValue(ps, i++, utente.getPassword() ) ; // "password" : java.lang.String
		setValue(ps, i++, utente.getAnagrafica() ) ; // "anagrafica" : java.lang.Long
		setValue(ps, i++, utente.getLivello() ) ; // "livello" : java.lang.Integer
		setValue(ps, i++, utente.getUltimologin() ) ; // "ultimoLogin" : java.util.Date
		setValue(ps, i++, utente.getCountloginfailed() ) ; // "countLoginFailed" : java.lang.Integer
		setValue(ps, i++, utente.getUtentebloccato() ) ; // "utenteBloccato" : java.lang.Boolean
		setValue(ps, i++, utente.getFirstLoginFailed() ) ; // "firstLoginFailed" : java.util.Date
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Utente utente) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, utente.getUser() ) ; // "user" : java.lang.String
		setValue(ps, i++, utente.getPassword() ) ; // "password" : java.lang.String
		setValue(ps, i++, utente.getAnagrafica() ) ; // "anagrafica" : java.lang.Long
		setValue(ps, i++, utente.getLivello() ) ; // "livello" : java.lang.Integer
		setValue(ps, i++, utente.getUltimologin() ) ; // "ultimoLogin" : java.util.Date
		setValue(ps, i++, utente.getCountloginfailed() ) ; // "countLoginFailed" : java.lang.Integer
		setValue(ps, i++, utente.getUtentebloccato() ) ; // "utenteBloccato" : java.lang.Boolean
		setValue(ps, i++, utente.getFirstLoginFailed() ) ; // "firstLoginFailed" : java.util.Date
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, utente.getId() ) ; // "id" : java.lang.Long
	}

}
