package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dao.LettoassistitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;
import org.riphouse.dto.Lettoassistito;

@Local(LettoassistitoDAO.class)
@Stateless
public class LettoassistitoDAOImpl extends GenericDAO<Lettoassistito> implements LettoassistitoDAO {

	private final static String SQL_SELECT = 
		"select id, Letto, Assistito from lettoassistito where id = ?";

	private final static String SQL_INSERT = 
		"insert into lettoassistito ( Letto, Assistito ) values ( ?, ? )";

	private final static String SQL_UPDATE = 
		"update lettoassistito set Letto = ?, Assistito = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from lettoassistito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from lettoassistito";

	private final static String SQL_COUNT = 
		"select count(*) from lettoassistito where id = ?";

	public LettoassistitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Lettoassistito newInstanceWithPrimaryKey( Long id ) {
		Lettoassistito lettoassistito = new Lettoassistito();
		lettoassistito.setId( id );
		return lettoassistito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Lettoassistito find( Long id ) {
		Lettoassistito lettoassistito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(lettoassistito) ) {
			return lettoassistito ;
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
	 * @param lettoassistito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Lettoassistito lettoassistito ) {
		return super.doSelect(lettoassistito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param lettoassistito
	 */
	@Override
	public Long insert(Lettoassistito lettoassistito) {
		Long key = super.doInsertAutoIncr(lettoassistito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param lettoassistito
	 * @return
	 */
	@Override
	public int update(Lettoassistito lettoassistito) {
		return super.doUpdate(lettoassistito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Lettoassistito lettoassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(lettoassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param lettoassistito
	 * @return
	 */
	@Override
	public int delete( Lettoassistito lettoassistito ) {
		return super.doDelete(lettoassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Lettoassistito lettoassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(lettoassistito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param lettoassistito
	 * @return
	 */
	@Override
	public boolean exists( Lettoassistito lettoassistito ) {
		return super.doExists(lettoassistito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Lettoassistito lettoassistito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, lettoassistito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Lettoassistito populateBean(ResultSet rs, Lettoassistito lettoassistito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		lettoassistito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { lettoassistito.setId(null); }; // not primitive number => keep null value if any
		lettoassistito.setLetto(rs.getLong("Letto")); // java.lang.Long
		if ( rs.wasNull() ) { lettoassistito.setLetto(null); }; // not primitive number => keep null value if any
		lettoassistito.setAssistito(rs.getLong("Assistito")); // java.lang.Long
		if ( rs.wasNull() ) { lettoassistito.setAssistito(null); }; // not primitive number => keep null value if any
		return lettoassistito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Lettoassistito lettoassistito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, lettoassistito.getLetto() ) ; // "Letto" : java.lang.Long
		setValue(ps, i++, lettoassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Lettoassistito lettoassistito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, lettoassistito.getLetto() ) ; // "Letto" : java.lang.Long
		setValue(ps, i++, lettoassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, lettoassistito.getId() ) ; // "id" : java.lang.Long
	}

}
