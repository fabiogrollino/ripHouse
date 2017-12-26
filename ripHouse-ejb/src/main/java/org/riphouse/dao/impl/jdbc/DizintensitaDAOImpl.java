package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dao.DizintensitaDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;
import org.riphouse.dto.Dizintensita;

@Local(DizintensitaDAO.class)
@Stateless
public class DizintensitaDAOImpl extends GenericDAO<Dizintensita> implements DizintensitaDAO {

	private final static String SQL_SELECT = 
		"select id, Descrizione from dizintensita where id = ?";

	private final static String SQL_INSERT = 
		"insert into dizintensita ( Descrizione ) values ( ? )";

	private final static String SQL_UPDATE = 
		"update dizintensita set Descrizione = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from dizintensita where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from dizintensita";

	private final static String SQL_COUNT = 
		"select count(*) from dizintensita where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public DizintensitaDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Dizintensita newInstanceWithPrimaryKey( Long id ) {
		Dizintensita dizintensita = new Dizintensita();
		dizintensita.setId( id );
		return dizintensita ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Dizintensita find( Long id ) {
		Dizintensita dizintensita = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(dizintensita) ) {
			return dizintensita ;
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
	 * @param dizintensita
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Dizintensita dizintensita ) {
		return super.doSelect(dizintensita) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param dizintensita
	 */
	@Override
	public Long insert(Dizintensita dizintensita) {
		Long key = super.doInsertAutoIncr(dizintensita);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param dizintensita
	 * @return
	 */
	@Override
	public int update(Dizintensita dizintensita) {
		return super.doUpdate(dizintensita);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Dizintensita dizintensita = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(dizintensita);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param dizintensita
	 * @return
	 */
	@Override
	public int delete( Dizintensita dizintensita ) {
		return super.doDelete(dizintensita);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Dizintensita dizintensita = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(dizintensita);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param dizintensita
	 * @return
	 */
	@Override
	public boolean exists( Dizintensita dizintensita ) {
		return super.doExists(dizintensita);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Dizintensita dizintensita) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizintensita.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Dizintensita populateBean(ResultSet rs, Dizintensita dizintensita) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		dizintensita.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { dizintensita.setId(null); }; // not primitive number => keep null value if any
		dizintensita.setDescrizione(rs.getString("Descrizione")); // java.lang.String
		return dizintensita ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Dizintensita dizintensita) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, dizintensita.getDescrizione() ) ; // "Descrizione" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Dizintensita dizintensita) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, dizintensita.getDescrizione() ) ; // "Descrizione" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, dizintensita.getId() ) ; // "id" : java.lang.Long
	}

}
