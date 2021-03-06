package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Caregiverassistito;
import org.riphouse.dao.CaregiverassistitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(CaregiverassistitoDAO.class)
@Stateless
public class CaregiverassistitoDAOImpl extends GenericDAO<Caregiverassistito> implements CaregiverassistitoDAO {

	private final static String SQL_SELECT = 
		"select id, CareGiver, Assistito, TipoCareGiver from caregiverassistito where id = ?";

	private final static String SQL_INSERT = 
		"insert into caregiverassistito ( CareGiver, Assistito, TipoCareGiver ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update caregiverassistito set CareGiver = ?, Assistito = ?, TipoCareGiver = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from caregiverassistito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from caregiverassistito";

	private final static String SQL_COUNT = 
		"select count(*) from caregiverassistito where id = ?";

	public CaregiverassistitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Caregiverassistito newInstanceWithPrimaryKey( Long id ) {
		Caregiverassistito caregiverassistito = new Caregiverassistito();
		caregiverassistito.setId( id );
		return caregiverassistito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Caregiverassistito find( Long id ) {
		Caregiverassistito caregiverassistito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(caregiverassistito) ) {
			return caregiverassistito ;
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
	 * @param caregiverassistito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Caregiverassistito caregiverassistito ) {
		return super.doSelect(caregiverassistito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param caregiverassistito
	 */
	@Override
	public Long insert(Caregiverassistito caregiverassistito) {
		Long key = super.doInsertAutoIncr(caregiverassistito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param caregiverassistito
	 * @return
	 */
	@Override
	public int update(Caregiverassistito caregiverassistito) {
		return super.doUpdate(caregiverassistito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Caregiverassistito caregiverassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(caregiverassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param caregiverassistito
	 * @return
	 */
	@Override
	public int delete( Caregiverassistito caregiverassistito ) {
		return super.doDelete(caregiverassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Caregiverassistito caregiverassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(caregiverassistito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param caregiverassistito
	 * @return
	 */
	@Override
	public boolean exists( Caregiverassistito caregiverassistito ) {
		return super.doExists(caregiverassistito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Caregiverassistito caregiverassistito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, caregiverassistito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Caregiverassistito populateBean(ResultSet rs, Caregiverassistito caregiverassistito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		caregiverassistito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { caregiverassistito.setId(null); }; // not primitive number => keep null value if any
		caregiverassistito.setCaregiver(rs.getLong("CareGiver")); // java.lang.Long
		if ( rs.wasNull() ) { caregiverassistito.setCaregiver(null); }; // not primitive number => keep null value if any
		caregiverassistito.setAssistito(rs.getLong("Assistito")); // java.lang.Long
		if ( rs.wasNull() ) { caregiverassistito.setAssistito(null); }; // not primitive number => keep null value if any
		caregiverassistito.setTipocaregiver(rs.getString("TipoCareGiver")); // java.lang.String
		return caregiverassistito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Caregiverassistito caregiverassistito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, caregiverassistito.getCaregiver() ) ; // "CareGiver" : java.lang.Long
		setValue(ps, i++, caregiverassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
		setValue(ps, i++, caregiverassistito.getTipocaregiver() ) ; // "TipoCareGiver" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Caregiverassistito caregiverassistito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, caregiverassistito.getCaregiver() ) ; // "CareGiver" : java.lang.Long
		setValue(ps, i++, caregiverassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
		setValue(ps, i++, caregiverassistito.getTipocaregiver() ) ; // "TipoCareGiver" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, caregiverassistito.getId() ) ; // "id" : java.lang.Long
	}

}
