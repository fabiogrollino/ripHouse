package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Medicoassistito;
import org.riphouse.dao.MedicoassistitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(MedicoassistitoDAO.class)
@Stateless
public class MedicoassistitoDAOImpl extends GenericDAO<Medicoassistito> implements MedicoassistitoDAO {

	private final static String SQL_SELECT = 
		"select id, Medico, Assistito from medicoassistito where id = ?";

	private final static String SQL_INSERT = 
		"insert into medicoassistito ( Medico, Assistito ) values ( ?, ? )";

	private final static String SQL_UPDATE = 
		"update medicoassistito set Medico = ?, Assistito = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from medicoassistito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from medicoassistito";

	private final static String SQL_COUNT = 
		"select count(*) from medicoassistito where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public MedicoassistitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Medicoassistito newInstanceWithPrimaryKey( Long id ) {
		Medicoassistito medicoassistito = new Medicoassistito();
		medicoassistito.setId( id );
		return medicoassistito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Medicoassistito find( Long id ) {
		Medicoassistito medicoassistito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(medicoassistito) ) {
			return medicoassistito ;
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
	 * @param medicoassistito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Medicoassistito medicoassistito ) {
		return super.doSelect(medicoassistito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param medicoassistito
	 */
	@Override
	public Long insert(Medicoassistito medicoassistito) {
		Long key = super.doInsertAutoIncr(medicoassistito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param medicoassistito
	 * @return
	 */
	@Override
	public int update(Medicoassistito medicoassistito) {
		return super.doUpdate(medicoassistito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Medicoassistito medicoassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(medicoassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param medicoassistito
	 * @return
	 */
	@Override
	public int delete( Medicoassistito medicoassistito ) {
		return super.doDelete(medicoassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Medicoassistito medicoassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(medicoassistito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param medicoassistito
	 * @return
	 */
	@Override
	public boolean exists( Medicoassistito medicoassistito ) {
		return super.doExists(medicoassistito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Medicoassistito medicoassistito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, medicoassistito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Medicoassistito populateBean(ResultSet rs, Medicoassistito medicoassistito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		medicoassistito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { medicoassistito.setId(null); }; // not primitive number => keep null value if any
		medicoassistito.setMedico(rs.getLong("Medico")); // java.lang.Long
		if ( rs.wasNull() ) { medicoassistito.setMedico(null); }; // not primitive number => keep null value if any
		medicoassistito.setAssistito(rs.getLong("Assistito")); // java.lang.Long
		if ( rs.wasNull() ) { medicoassistito.setAssistito(null); }; // not primitive number => keep null value if any
		return medicoassistito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Medicoassistito medicoassistito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, medicoassistito.getMedico() ) ; // "Medico" : java.lang.Long
		setValue(ps, i++, medicoassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Medicoassistito medicoassistito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, medicoassistito.getMedico() ) ; // "Medico" : java.lang.Long
		setValue(ps, i++, medicoassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, medicoassistito.getId() ) ; // "id" : java.lang.Long
	}

}
