package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Dizpiano;
import org.riphouse.dao.DizpianoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(DizpianoDAO.class)
@Stateless
public class DizpianoDAOImpl extends GenericDAO<Dizpiano> implements DizpianoDAO {

	private final static String SQL_SELECT = 
		"select id, Descrizione from dizpiano where id = ?";

	private final static String SQL_INSERT = 
		"insert into dizpiano ( id, Descrizione ) values ( ?, ? )";

	private final static String SQL_UPDATE = 
		"update dizpiano set Descrizione = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from dizpiano where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from dizpiano";

	private final static String SQL_COUNT = 
		"select count(*) from dizpiano where id = ?";

	public DizpianoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Dizpiano newInstanceWithPrimaryKey( Long id ) {
		Dizpiano dizpiano = new Dizpiano();
		dizpiano.setId( id );
		return dizpiano ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Dizpiano find( Long id ) {
		Dizpiano dizpiano = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(dizpiano) ) {
			return dizpiano ;
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
	 * @param dizpiano
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Dizpiano dizpiano ) {
		return super.doSelect(dizpiano) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param dizpiano
	 */
	@Override
	public void insert(Dizpiano dizpiano) {
		super.doInsert(dizpiano);
	}	

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param dizpiano
	 * @return
	 */
	@Override
	public int update(Dizpiano dizpiano) {
		return super.doUpdate(dizpiano);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Dizpiano dizpiano = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(dizpiano);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param dizpiano
	 * @return
	 */
	@Override
	public int delete( Dizpiano dizpiano ) {
		return super.doDelete(dizpiano);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Dizpiano dizpiano = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(dizpiano);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param dizpiano
	 * @return
	 */
	@Override
	public boolean exists( Dizpiano dizpiano ) {
		return super.doExists(dizpiano);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Dizpiano dizpiano) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizpiano.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Dizpiano populateBean(ResultSet rs, Dizpiano dizpiano) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		dizpiano.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { dizpiano.setId(null); }; // not primitive number => keep null value if any
		dizpiano.setDescrizione(rs.getString("Descrizione")); // java.lang.String
		return dizpiano ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Dizpiano dizpiano) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, dizpiano.getId() ) ; // "id" : java.lang.Long
		setValue(ps, i++, dizpiano.getDescrizione() ) ; // "Descrizione" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Dizpiano dizpiano) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, dizpiano.getDescrizione() ) ; // "Descrizione" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizpiano.getId() ) ; // "id" : java.lang.Long
	}

}
