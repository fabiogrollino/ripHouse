package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.riphouse.dto.Anagrafica;
import org.riphouse.dao.AnagraficaDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

public class AnagraficaDAOImpl extends GenericDAO<Anagrafica> implements AnagraficaDAO {

	private final static String SQL_SELECT = 
		"select id, Nome, Cognome, CodiceFiscale, DataNascita, ComuneNascita from anagrafica where id = ?";

	private final static String SQL_INSERT = 
		"insert into anagrafica ( Nome, Cognome, CodiceFiscale, DataNascita, ComuneNascita ) values ( ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update anagrafica set Nome = ?, Cognome = ?, CodiceFiscale = ?, DataNascita = ?, ComuneNascita = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from anagrafica where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from anagrafica";

	private final static String SQL_COUNT = 
		"select count(*) from anagrafica where id = ?";

	public AnagraficaDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Anagrafica newInstanceWithPrimaryKey( Long id ) {
		Anagrafica anagrafica = new Anagrafica();
		anagrafica.setId( id );
		return anagrafica ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Anagrafica find( Long id ) {
		Anagrafica anagrafica = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(anagrafica) ) {
			return anagrafica ;
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
	 * @param anagrafica
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Anagrafica anagrafica ) {
		return super.doSelect(anagrafica) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param anagrafica
	 */
	@Override
	public Long insert(Anagrafica anagrafica) {
		Long key = super.doInsertAutoIncr(anagrafica);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param anagrafica
	 * @return
	 */
	@Override
	public int update(Anagrafica anagrafica) {
		return super.doUpdate(anagrafica);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Anagrafica anagrafica = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(anagrafica);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param anagrafica
	 * @return
	 */
	@Override
	public int delete( Anagrafica anagrafica ) {
		return super.doDelete(anagrafica);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Anagrafica anagrafica = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(anagrafica);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param anagrafica
	 * @return
	 */
	@Override
	public boolean exists( Anagrafica anagrafica ) {
		return super.doExists(anagrafica);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Anagrafica anagrafica) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, anagrafica.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Anagrafica populateBean(ResultSet rs, Anagrafica anagrafica) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		anagrafica.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { anagrafica.setId(null); }; // not primitive number => keep null value if any
		anagrafica.setNome(rs.getString("Nome")); // java.lang.String
		anagrafica.setCognome(rs.getString("Cognome")); // java.lang.String
		anagrafica.setCodicefiscale(rs.getString("CodiceFiscale")); // java.lang.String
		anagrafica.setDatanascita(rs.getDate("DataNascita")); // java.util.Date
		anagrafica.setComunenascita(rs.getString("ComuneNascita")); // java.lang.String
		return anagrafica ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Anagrafica anagrafica) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, anagrafica.getNome() ) ; // "Nome" : java.lang.String
		setValue(ps, i++, anagrafica.getCognome() ) ; // "Cognome" : java.lang.String
		setValue(ps, i++, anagrafica.getCodicefiscale() ) ; // "CodiceFiscale" : java.lang.String
		setValue(ps, i++, anagrafica.getDatanascita() ) ; // "DataNascita" : java.util.Date
		setValue(ps, i++, anagrafica.getComunenascita() ) ; // "ComuneNascita" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Anagrafica anagrafica) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, anagrafica.getNome() ) ; // "Nome" : java.lang.String
		setValue(ps, i++, anagrafica.getCognome() ) ; // "Cognome" : java.lang.String
		setValue(ps, i++, anagrafica.getCodicefiscale() ) ; // "CodiceFiscale" : java.lang.String
		setValue(ps, i++, anagrafica.getDatanascita() ) ; // "DataNascita" : java.util.Date
		setValue(ps, i++, anagrafica.getComunenascita() ) ; // "ComuneNascita" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, anagrafica.getId() ) ; // "id" : java.lang.Long
	}

}
