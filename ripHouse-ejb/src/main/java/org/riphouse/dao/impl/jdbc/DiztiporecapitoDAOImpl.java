package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Diztiporecapito;
import org.riphouse.dao.DiztiporecapitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(DiztiporecapitoDAO.class)
@Stateless
public class DiztiporecapitoDAOImpl extends GenericDAO<Diztiporecapito> implements DiztiporecapitoDAO {

	private final static String SQL_SELECT = 
		"select id, TipoRecapito from diztiporecapito where id = ?";

	private final static String SQL_INSERT = 
		"insert into diztiporecapito ( TipoRecapito ) values ( ? )";

	private final static String SQL_UPDATE = 
		"update diztiporecapito set TipoRecapito = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from diztiporecapito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from diztiporecapito";

	private final static String SQL_COUNT = 
		"select count(*) from diztiporecapito where id = ?";

	public DiztiporecapitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Diztiporecapito newInstanceWithPrimaryKey( Long id ) {
		Diztiporecapito diztiporecapito = new Diztiporecapito();
		diztiporecapito.setId( id );
		return diztiporecapito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Diztiporecapito find( Long id ) {
		Diztiporecapito diztiporecapito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(diztiporecapito) ) {
			return diztiporecapito ;
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
	 * @param diztiporecapito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Diztiporecapito diztiporecapito ) {
		return super.doSelect(diztiporecapito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param diztiporecapito
	 */
	@Override
	public Long insert(Diztiporecapito diztiporecapito) {
		Long key = super.doInsertAutoIncr(diztiporecapito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param diztiporecapito
	 * @return
	 */
	@Override
	public int update(Diztiporecapito diztiporecapito) {
		return super.doUpdate(diztiporecapito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Diztiporecapito diztiporecapito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(diztiporecapito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param diztiporecapito
	 * @return
	 */
	@Override
	public int delete( Diztiporecapito diztiporecapito ) {
		return super.doDelete(diztiporecapito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Diztiporecapito diztiporecapito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(diztiporecapito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param diztiporecapito
	 * @return
	 */
	@Override
	public boolean exists( Diztiporecapito diztiporecapito ) {
		return super.doExists(diztiporecapito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Diztiporecapito diztiporecapito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, diztiporecapito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Diztiporecapito populateBean(ResultSet rs, Diztiporecapito diztiporecapito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		diztiporecapito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { diztiporecapito.setId(null); }; // not primitive number => keep null value if any
		diztiporecapito.setTiporecapito(rs.getString("TipoRecapito")); // java.lang.String
		return diztiporecapito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Diztiporecapito diztiporecapito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, diztiporecapito.getTiporecapito() ) ; // "TipoRecapito" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Diztiporecapito diztiporecapito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, diztiporecapito.getTiporecapito() ) ; // "TipoRecapito" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, diztiporecapito.getId() ) ; // "id" : java.lang.Long
	}

}
