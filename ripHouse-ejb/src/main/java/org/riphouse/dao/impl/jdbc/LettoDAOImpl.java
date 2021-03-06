package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Letto;
import org.riphouse.dao.LettoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(LettoDAO.class)
@Stateless
public class LettoDAOImpl extends GenericDAO<Letto> implements LettoDAO {

	private final static String SQL_SELECT = 
		"select id, NumeroLetto, Camera, Telefono from letto where id = ?";

	private final static String SQL_INSERT = 
		"insert into letto ( NumeroLetto, Camera, Telefono ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update letto set NumeroLetto = ?, Camera = ?, Telefono = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from letto where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from letto";

	private final static String SQL_COUNT = 
		"select count(*) from letto where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public LettoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Letto newInstanceWithPrimaryKey( Long id ) {
		Letto letto = new Letto();
		letto.setId( id );
		return letto ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Letto find( Long id ) {
		Letto letto = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(letto) ) {
			return letto ;
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
	 * @param letto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Letto letto ) {
		return super.doSelect(letto) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param letto
	 */
	@Override
	public Long insert(Letto letto) {
		Long key = super.doInsertAutoIncr(letto);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param letto
	 * @return
	 */
	@Override
	public int update(Letto letto) {
		return super.doUpdate(letto);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Letto letto = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(letto);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param letto
	 * @return
	 */
	@Override
	public int delete( Letto letto ) {
		return super.doDelete(letto);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Letto letto = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(letto);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param letto
	 * @return
	 */
	@Override
	public boolean exists( Letto letto ) {
		return super.doExists(letto);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Letto letto) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, letto.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Letto populateBean(ResultSet rs, Letto letto) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		letto.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { letto.setId(null); }; // not primitive number => keep null value if any
		letto.setNumeroletto(rs.getString("NumeroLetto")); // java.lang.String
		letto.setCamera(rs.getLong("Camera")); // java.lang.Long
		if ( rs.wasNull() ) { letto.setCamera(null); }; // not primitive number => keep null value if any
		letto.setTelefono(rs.getString("Telefono")); // java.lang.String
		return letto ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Letto letto) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, letto.getNumeroletto() ) ; // "NumeroLetto" : java.lang.String
		setValue(ps, i++, letto.getCamera() ) ; // "Camera" : java.lang.Long
		setValue(ps, i++, letto.getTelefono() ) ; // "Telefono" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Letto letto) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, letto.getNumeroletto() ) ; // "NumeroLetto" : java.lang.String
		setValue(ps, i++, letto.getCamera() ) ; // "Camera" : java.lang.Long
		setValue(ps, i++, letto.getTelefono() ) ; // "Telefono" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, letto.getId() ) ; // "id" : java.lang.Long
	}

}
