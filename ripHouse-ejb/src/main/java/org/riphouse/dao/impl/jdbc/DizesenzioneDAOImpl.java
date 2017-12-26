package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Dizesenzione;
import org.riphouse.dao.DizesenzioneDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(DizesenzioneDAO.class)
@Stateless
public class DizesenzioneDAOImpl extends GenericDAO<Dizesenzione> implements DizesenzioneDAO {

	private final static String SQL_SELECT = 
		"select id, Descrizione from dizesenzione where id = ?";

	private final static String SQL_INSERT = 
		"insert into dizesenzione ( Descrizione ) values ( ? )";

	private final static String SQL_UPDATE = 
		"update dizesenzione set Descrizione = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from dizesenzione where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from dizesenzione";

	private final static String SQL_COUNT = 
		"select count(*) from dizesenzione where id = ?";

	public DizesenzioneDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Dizesenzione newInstanceWithPrimaryKey( Long id ) {
		Dizesenzione dizesenzione = new Dizesenzione();
		dizesenzione.setId( id );
		return dizesenzione ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Dizesenzione find( Long id ) {
		Dizesenzione dizesenzione = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(dizesenzione) ) {
			return dizesenzione ;
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
	 * @param dizesenzione
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Dizesenzione dizesenzione ) {
		return super.doSelect(dizesenzione) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param dizesenzione
	 */
	@Override
	public Long insert(Dizesenzione dizesenzione) {
		Long key = super.doInsertAutoIncr(dizesenzione);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param dizesenzione
	 * @return
	 */
	@Override
	public int update(Dizesenzione dizesenzione) {
		return super.doUpdate(dizesenzione);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Dizesenzione dizesenzione = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(dizesenzione);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param dizesenzione
	 * @return
	 */
	@Override
	public int delete( Dizesenzione dizesenzione ) {
		return super.doDelete(dizesenzione);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Dizesenzione dizesenzione = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(dizesenzione);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param dizesenzione
	 * @return
	 */
	@Override
	public boolean exists( Dizesenzione dizesenzione ) {
		return super.doExists(dizesenzione);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Dizesenzione dizesenzione) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizesenzione.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Dizesenzione populateBean(ResultSet rs, Dizesenzione dizesenzione) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		dizesenzione.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { dizesenzione.setId(null); }; // not primitive number => keep null value if any
		dizesenzione.setDescrizione(rs.getString("Descrizione")); // java.lang.String
		return dizesenzione ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Dizesenzione dizesenzione) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, dizesenzione.getDescrizione() ) ; // "Descrizione" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Dizesenzione dizesenzione) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, dizesenzione.getDescrizione() ) ; // "Descrizione" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizesenzione.getId() ) ; // "id" : java.lang.Long
	}

}
