package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Reparto;
import org.riphouse.dao.RepartoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(RepartoDAO.class)
@Stateless
public class RepartoDAOImpl extends GenericDAO<Reparto> implements RepartoDAO {

	private final static String SQL_SELECT = 
		"select id, NomeReparto, Piano, Residenza from reparto where id = ?";

	private final static String SQL_INSERT = 
		"insert into reparto ( NomeReparto, Piano, Residenza ) values ( ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update reparto set NomeReparto = ?, Piano = ?, Residenza = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from reparto where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from reparto";

	private final static String SQL_COUNT = 
		"select count(*) from reparto where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public RepartoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Reparto newInstanceWithPrimaryKey( Long id ) {
		Reparto reparto = new Reparto();
		reparto.setId( id );
		return reparto ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Reparto find( Long id ) {
		Reparto reparto = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(reparto) ) {
			return reparto ;
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
	 * @param reparto
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Reparto reparto ) {
		return super.doSelect(reparto) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param reparto
	 */
	@Override
	public Long insert(Reparto reparto) {
		Long key = super.doInsertAutoIncr(reparto);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param reparto
	 * @return
	 */
	@Override
	public int update(Reparto reparto) {
		return super.doUpdate(reparto);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Reparto reparto = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(reparto);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param reparto
	 * @return
	 */
	@Override
	public int delete( Reparto reparto ) {
		return super.doDelete(reparto);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Reparto reparto = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(reparto);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param reparto
	 * @return
	 */
	@Override
	public boolean exists( Reparto reparto ) {
		return super.doExists(reparto);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Reparto reparto) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, reparto.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Reparto populateBean(ResultSet rs, Reparto reparto) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		reparto.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { reparto.setId(null); }; // not primitive number => keep null value if any
		reparto.setNomereparto(rs.getString("NomeReparto")); // java.lang.String
		reparto.setPiano(rs.getLong("Piano")); // java.lang.Long
		if ( rs.wasNull() ) { reparto.setPiano(null); }; // not primitive number => keep null value if any
		reparto.setResidenza(rs.getLong("Residenza")); // java.lang.Long
		if ( rs.wasNull() ) { reparto.setResidenza(null); }; // not primitive number => keep null value if any
		return reparto ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Reparto reparto) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, reparto.getNomereparto() ) ; // "NomeReparto" : java.lang.String
		setValue(ps, i++, reparto.getPiano() ) ; // "Piano" : java.lang.Long
		setValue(ps, i++, reparto.getResidenza() ) ; // "Residenza" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Reparto reparto) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, reparto.getNomereparto() ) ; // "NomeReparto" : java.lang.String
		setValue(ps, i++, reparto.getPiano() ) ; // "Piano" : java.lang.Long
		setValue(ps, i++, reparto.getResidenza() ) ; // "Residenza" : java.lang.Long
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, reparto.getId() ) ; // "id" : java.lang.Long
	}

}
