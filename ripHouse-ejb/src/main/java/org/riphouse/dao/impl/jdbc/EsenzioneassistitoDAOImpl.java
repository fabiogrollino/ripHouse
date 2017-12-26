package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.riphouse.dto.Esenzioneassistito;
import org.riphouse.dao.EsenzioneassistitoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

public class EsenzioneassistitoDAOImpl extends GenericDAO<Esenzioneassistito> implements EsenzioneassistitoDAO {

	private final static String SQL_SELECT = 
		"select id, Esenzione, Assistito from esenzioneassistito where id = ?";

	private final static String SQL_INSERT = 
		"insert into esenzioneassistito ( Esenzione, Assistito ) values ( ?, ? )";

	private final static String SQL_UPDATE = 
		"update esenzioneassistito set Esenzione = ?, Assistito = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from esenzioneassistito where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from esenzioneassistito";

	private final static String SQL_COUNT = 
		"select count(*) from esenzioneassistito where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public EsenzioneassistitoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Esenzioneassistito newInstanceWithPrimaryKey( Long id ) {
		Esenzioneassistito esenzioneassistito = new Esenzioneassistito();
		esenzioneassistito.setId( id );
		return esenzioneassistito ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Esenzioneassistito find( Long id ) {
		Esenzioneassistito esenzioneassistito = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(esenzioneassistito) ) {
			return esenzioneassistito ;
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
	 * @param esenzioneassistito
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Esenzioneassistito esenzioneassistito ) {
		return super.doSelect(esenzioneassistito) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param esenzioneassistito
	 */
	@Override
	public Long insert(Esenzioneassistito esenzioneassistito) {
		Long key = super.doInsertAutoIncr(esenzioneassistito);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param esenzioneassistito
	 * @return
	 */
	@Override
	public int update(Esenzioneassistito esenzioneassistito) {
		return super.doUpdate(esenzioneassistito);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Esenzioneassistito esenzioneassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(esenzioneassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param esenzioneassistito
	 * @return
	 */
	@Override
	public int delete( Esenzioneassistito esenzioneassistito ) {
		return super.doDelete(esenzioneassistito);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Esenzioneassistito esenzioneassistito = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(esenzioneassistito);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param esenzioneassistito
	 * @return
	 */
	@Override
	public boolean exists( Esenzioneassistito esenzioneassistito ) {
		return super.doExists(esenzioneassistito);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Esenzioneassistito esenzioneassistito) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, esenzioneassistito.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Esenzioneassistito populateBean(ResultSet rs, Esenzioneassistito esenzioneassistito) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		esenzioneassistito.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { esenzioneassistito.setId(null); }; // not primitive number => keep null value if any
		esenzioneassistito.setEsenzione(rs.getLong("Esenzione")); // java.lang.Long
		if ( rs.wasNull() ) { esenzioneassistito.setEsenzione(null); }; // not primitive number => keep null value if any
		esenzioneassistito.setAssistito(rs.getLong("Assistito")); // java.lang.Long
		if ( rs.wasNull() ) { esenzioneassistito.setAssistito(null); }; // not primitive number => keep null value if any
		return esenzioneassistito ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Esenzioneassistito esenzioneassistito) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, esenzioneassistito.getEsenzione() ) ; // "Esenzione" : java.lang.Long
		setValue(ps, i++, esenzioneassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Esenzioneassistito esenzioneassistito) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, esenzioneassistito.getEsenzione() ) ; // "Esenzione" : java.lang.Long
		setValue(ps, i++, esenzioneassistito.getAssistito() ) ; // "Assistito" : java.lang.Long
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, esenzioneassistito.getId() ) ; // "id" : java.lang.Long
	}

}
