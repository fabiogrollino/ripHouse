package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Diztipologiaassistenziale;
import org.riphouse.dao.DiztipologiaassistenzialeDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(DiztipologiaassistenzialeDAO.class)
@Stateless
public class DiztipologiaassistenzialeDAOImpl extends GenericDAO<Diztipologiaassistenziale> implements DiztipologiaassistenzialeDAO {

	private final static String SQL_SELECT = 
		"select id, Descrizione from diztipologiaassistenziale where id = ?";

	private final static String SQL_INSERT = 
		"insert into diztipologiaassistenziale ( Descrizione ) values ( ? )";

	private final static String SQL_UPDATE = 
		"update diztipologiaassistenziale set Descrizione = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from diztipologiaassistenziale where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from diztipologiaassistenziale";

	private final static String SQL_COUNT = 
		"select count(*) from diztipologiaassistenziale where id = ?";

	public DiztipologiaassistenzialeDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Diztipologiaassistenziale newInstanceWithPrimaryKey( Long id ) {
		Diztipologiaassistenziale diztipologiaassistenziale = new Diztipologiaassistenziale();
		diztipologiaassistenziale.setId( id );
		return diztipologiaassistenziale ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Diztipologiaassistenziale find( Long id ) {
		Diztipologiaassistenziale diztipologiaassistenziale = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(diztipologiaassistenziale) ) {
			return diztipologiaassistenziale ;
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
	 * @param diztipologiaassistenziale
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Diztipologiaassistenziale diztipologiaassistenziale ) {
		return super.doSelect(diztipologiaassistenziale) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param diztipologiaassistenziale
	 */
	@Override
	public Long insert(Diztipologiaassistenziale diztipologiaassistenziale) {
		Long key = super.doInsertAutoIncr(diztipologiaassistenziale);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param diztipologiaassistenziale
	 * @return
	 */
	@Override
	public int update(Diztipologiaassistenziale diztipologiaassistenziale) {
		return super.doUpdate(diztipologiaassistenziale);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Diztipologiaassistenziale diztipologiaassistenziale = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(diztipologiaassistenziale);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param diztipologiaassistenziale
	 * @return
	 */
	@Override
	public int delete( Diztipologiaassistenziale diztipologiaassistenziale ) {
		return super.doDelete(diztipologiaassistenziale);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Diztipologiaassistenziale diztipologiaassistenziale = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(diztipologiaassistenziale);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param diztipologiaassistenziale
	 * @return
	 */
	@Override
	public boolean exists( Diztipologiaassistenziale diztipologiaassistenziale ) {
		return super.doExists(diztipologiaassistenziale);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Diztipologiaassistenziale diztipologiaassistenziale) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, diztipologiaassistenziale.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Diztipologiaassistenziale populateBean(ResultSet rs, Diztipologiaassistenziale diztipologiaassistenziale) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		diztipologiaassistenziale.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { diztipologiaassistenziale.setId(null); }; // not primitive number => keep null value if any
		diztipologiaassistenziale.setDescrizione(rs.getString("Descrizione")); // java.lang.String
		return diztipologiaassistenziale ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Diztipologiaassistenziale diztipologiaassistenziale) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, diztipologiaassistenziale.getDescrizione() ) ; // "Descrizione" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Diztipologiaassistenziale diztipologiaassistenziale) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, diztipologiaassistenziale.getDescrizione() ) ; // "Descrizione" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, diztipologiaassistenziale.getId() ) ; // "id" : java.lang.Long
	}

}
