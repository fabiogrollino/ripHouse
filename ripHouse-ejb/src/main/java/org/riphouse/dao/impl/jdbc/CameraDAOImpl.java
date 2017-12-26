package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Camera;
import org.riphouse.dao.CameraDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(CameraDAO.class)
@Stateless
public class CameraDAOImpl extends GenericDAO<Camera> implements CameraDAO {

	private final static String SQL_SELECT = 
		"select id, Repato, NumeroPosti, NumeroCamera from camera where id = ?";

	private final static String SQL_INSERT = 
		"insert into camera ( Repato, NumeroPosti, NumeroCamera ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update camera set Repato = ?, NumeroPosti = ?, NumeroCamera = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from camera where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from camera";

	private final static String SQL_COUNT = 
		"select count(*) from camera where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public CameraDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Camera newInstanceWithPrimaryKey( Long id ) {
		Camera camera = new Camera();
		camera.setId( id );
		return camera ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Camera find( Long id ) {
		Camera camera = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(camera) ) {
			return camera ;
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
	 * @param camera
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Camera camera ) {
		return super.doSelect(camera) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param camera
	 */
	@Override
	public Long insert(Camera camera) {
		Long key = super.doInsertAutoIncr(camera);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param camera
	 * @return
	 */
	@Override
	public int update(Camera camera) {
		return super.doUpdate(camera);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Camera camera = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(camera);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param camera
	 * @return
	 */
	@Override
	public int delete( Camera camera ) {
		return super.doDelete(camera);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Camera camera = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(camera);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param camera
	 * @return
	 */
	@Override
	public boolean exists( Camera camera ) {
		return super.doExists(camera);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Camera camera) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, camera.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Camera populateBean(ResultSet rs, Camera camera) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		camera.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { camera.setId(null); }; // not primitive number => keep null value if any
		camera.setRepato(rs.getLong("Repato")); // java.lang.Long
		if ( rs.wasNull() ) { camera.setRepato(null); }; // not primitive number => keep null value if any
		camera.setNumeroposti(rs.getInt("NumeroPosti")); // java.lang.Integer
		if ( rs.wasNull() ) { camera.setNumeroposti(null); }; // not primitive number => keep null value if any
		camera.setNumerocamera(rs.getInt("NumeroCamera")); // java.lang.Integer
		if ( rs.wasNull() ) { camera.setNumerocamera(null); }; // not primitive number => keep null value if any
		return camera ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Camera camera) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, camera.getRepato() ) ; // "Repato" : java.lang.Long
		setValue(ps, i++, camera.getNumeroposti() ) ; // "NumeroPosti" : java.lang.Integer
		setValue(ps, i++, camera.getNumerocamera() ) ; // "NumeroCamera" : java.lang.Integer
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Camera camera) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, camera.getRepato() ) ; // "Repato" : java.lang.Long
		setValue(ps, i++, camera.getNumeroposti() ) ; // "NumeroPosti" : java.lang.Integer
		setValue(ps, i++, camera.getNumerocamera() ) ; // "NumeroCamera" : java.lang.Integer
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, camera.getId() ) ; // "id" : java.lang.Long
	}

}
