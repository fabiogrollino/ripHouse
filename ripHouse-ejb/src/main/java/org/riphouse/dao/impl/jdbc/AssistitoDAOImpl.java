package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Assistito;
import org.riphouse.dao.AssistitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(AssistitoDAO.class)
@Stateless
public class AssistitoDAOImpl extends GenericDAO<Assistito> implements AssistitoDAO {

	private final static String SQL_SELECT = 
		"select id, anagrafica, dataScadenzaLibrettoSanitario from assistito where id = ?";

	private final static String SQL_INSERT = 
		"insert into assistito ( anagrafica, dataScadenzaLibrettoSanitario ) values ( ?, ? )";

	private final static String SQL_UPDATE = 
		"update assistito set anagrafica = ?, dataScadenzaLibrettoSanitario = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from assistito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from assistito";

	private final static String SQL_COUNT = 
		"select count(*) from assistito where id = ?";

	public AssistitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Assistito newInstanceWithPrimaryKey( Long id ) {
		Assistito assistito = new Assistito();
		assistito.setId( id );
		return assistito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Assistito find( Long id ) {
		Assistito assistito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(assistito) ) {
			return assistito ;
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
	 * @param assistito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Assistito assistito ) {
		return super.doSelect(assistito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param assistito
	 */
	@Override
	public Long insert(Assistito assistito) {
		Long key = super.doInsertAutoIncr(assistito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param assistito
	 * @return
	 */
	@Override
	public int update(Assistito assistito) {
		return super.doUpdate(assistito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Assistito assistito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(assistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param assistito
	 * @return
	 */
	@Override
	public int delete( Assistito assistito ) {
		return super.doDelete(assistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Assistito assistito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(assistito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param assistito
	 * @return
	 */
	@Override
	public boolean exists( Assistito assistito ) {
		return super.doExists(assistito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Assistito assistito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, assistito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Assistito populateBean(ResultSet rs, Assistito assistito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		assistito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { assistito.setId(null); }; // not primitive number => keep null value if any
		assistito.setAnagrafica(rs.getLong("anagrafica")); // java.lang.Long
		if ( rs.wasNull() ) { assistito.setAnagrafica(null); }; // not primitive number => keep null value if any
		assistito.setDatascadenzalibrettosanitario(rs.getDate("dataScadenzaLibrettoSanitario")); // java.util.Date
		return assistito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Assistito assistito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, assistito.getAnagrafica() ) ; // "anagrafica" : java.lang.Long
		setValue(ps, i++, assistito.getDatascadenzalibrettosanitario() ) ; // "dataScadenzaLibrettoSanitario" : java.util.Date
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Assistito assistito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, assistito.getAnagrafica() ) ; // "anagrafica" : java.lang.Long
		setValue(ps, i++, assistito.getDatascadenzalibrettosanitario() ) ; // "dataScadenzaLibrettoSanitario" : java.util.Date
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, assistito.getId() ) ; // "id" : java.lang.Long
	}

}
