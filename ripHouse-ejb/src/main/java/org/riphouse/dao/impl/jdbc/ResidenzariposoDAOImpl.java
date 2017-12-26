package org.riphouse.dao.impl.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.riphouse.dto.Residenzariposo;
import org.riphouse.dao.ResidenzariposoDAO;
import org.riphouse.dao.impl.jdbc.commons.GenericDAO;

@Local(ResidenzariposoDAO.class)
@Stateless
public class ResidenzariposoDAOImpl extends GenericDAO<Residenzariposo> implements ResidenzariposoDAO {

	private final static String SQL_SELECT = 
		"select id, NomeResidenza, NumeroPosti, Indirizzo, Comune, Cap from residenzariposo where id = ?";

	private final static String SQL_INSERT = 
		"insert into residenzariposo ( NomeResidenza, NumeroPosti, Indirizzo, Comune, Cap ) values ( ?, ?, ?, ?, ? )";

	private final static String SQL_UPDATE = 
		"update residenzariposo set NomeResidenza = ?, NumeroPosti = ?, Indirizzo = ?, Comune = ?, Cap = ? where id = ?";

	private final static String SQL_DELETE = 
		"delete from residenzariposo where id = ?";

	private final static String SQL_COUNT_ALL = 
		"select count(*) from residenzariposo";

	private final static String SQL_COUNT = 
		"select count(*) from residenzariposo where id = ?";

    //----------------------------------------------------------------------
	/**
	 * DAO constructor
	 */
	public ResidenzariposoDAOImpl() {
		super();
	}

	//----------------------------------------------------------------------
	/**
	 * Creates a new instance of the bean and populates it with the given primary value(s)
	 * @param id;
	 * @return the new instance
	 */
	private Residenzariposo newInstanceWithPrimaryKey( Long id ) {
		Residenzariposo residenzariposo = new Residenzariposo();
		residenzariposo.setId( id );
		return residenzariposo ;
	}

	//----------------------------------------------------------------------
	/**
	 * Finds a bean by its primary key 
	 * @param id;
	 * @return the bean found or null if not found 
	 */
	@Override
	public Residenzariposo find( Long id ) {
		Residenzariposo residenzariposo = newInstanceWithPrimaryKey( id ) ;
		if ( super.doSelect(residenzariposo) ) {
			return residenzariposo ;
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
	 * @param residenzariposo
	 * @return true if found, false if not found
	 */
	@Override
	public boolean load( Residenzariposo residenzariposo ) {
		return super.doSelect(residenzariposo) ;
	}
    //----------------------------------------------------------------------
	/**
	 * Inserts the given bean in the database 
	 * @param residenzariposo
	 */
	@Override
	public Long insert(Residenzariposo residenzariposo) {
		Long key = super.doInsertAutoIncr(residenzariposo);
		return key.longValue();
	}

    //----------------------------------------------------------------------
	/**
	 * Updates the given bean in the database 
	 * @param residenzariposo
	 * @return
	 */
	@Override
	public int update(Residenzariposo residenzariposo) {
		return super.doUpdate(residenzariposo);
	}	

    //----------------------------------------------------------------------
	/**
	 * Deletes the record in the database using the given primary key value(s) 
	 * @param id;
	 * @return
	 */
	@Override
	public int delete( Long id ) {
		Residenzariposo residenzariposo = newInstanceWithPrimaryKey( id ) ;
		return super.doDelete(residenzariposo);
	}

    //----------------------------------------------------------------------
	/**
	 * Deletes the given bean in the database 
	 * @param residenzariposo
	 * @return
	 */
	@Override
	public int delete( Residenzariposo residenzariposo ) {
		return super.doDelete(residenzariposo);
	}

    //----------------------------------------------------------------------
	/**
	 * Checks the existence of a record in the database using the given primary key value(s)
	 * @param id;
	 * @return
	 */
	@Override
	public boolean exists( Long id ) {
		Residenzariposo residenzariposo = newInstanceWithPrimaryKey( id ) ;
		return super.doExists(residenzariposo);
	}
    //----------------------------------------------------------------------
	/**
	 * Checks the existence of the given bean in the database 
	 * @param residenzariposo
	 * @return
	 */
	@Override
	public boolean exists( Residenzariposo residenzariposo ) {
		return super.doExists(residenzariposo);
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
	protected void setValuesForPrimaryKey(PreparedStatement ps, int i, Residenzariposo residenzariposo) throws SQLException {
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, residenzariposo.getId() ) ; // "id" : java.lang.Long
	}

    //----------------------------------------------------------------------
	@Override
	protected Residenzariposo populateBean(ResultSet rs, Residenzariposo residenzariposo) throws SQLException {

		//--- Set data from ResultSet to Bean attributes
		residenzariposo.setId(rs.getLong("id")); // java.lang.Long
		if ( rs.wasNull() ) { residenzariposo.setId(null); }; // not primitive number => keep null value if any
		residenzariposo.setNomeresidenza(rs.getString("NomeResidenza")); // java.lang.String
		residenzariposo.setNumeroposti(rs.getInt("NumeroPosti")); // java.lang.Integer
		if ( rs.wasNull() ) { residenzariposo.setNumeroposti(null); }; // not primitive number => keep null value if any
		residenzariposo.setIndirizzo(rs.getString("Indirizzo")); // java.lang.String
		residenzariposo.setComune(rs.getString("Comune")); // java.lang.String
		residenzariposo.setCap(rs.getString("Cap")); // java.lang.String
		return residenzariposo ;
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForInsert(PreparedStatement ps, int i, Residenzariposo residenzariposo) throws SQLException {

		//--- Set PRIMARY KEY and DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		// "id" is auto-incremented => no set in insert		
		setValue(ps, i++, residenzariposo.getNomeresidenza() ) ; // "NomeResidenza" : java.lang.String
		setValue(ps, i++, residenzariposo.getNumeroposti() ) ; // "NumeroPosti" : java.lang.Integer
		setValue(ps, i++, residenzariposo.getIndirizzo() ) ; // "Indirizzo" : java.lang.String
		setValue(ps, i++, residenzariposo.getComune() ) ; // "Comune" : java.lang.String
		setValue(ps, i++, residenzariposo.getCap() ) ; // "Cap" : java.lang.String
	}

    //----------------------------------------------------------------------
	@Override
	protected void setValuesForUpdate(PreparedStatement ps, int i, Residenzariposo residenzariposo) throws SQLException {

		//--- Set DATA from bean to PreparedStatement ( SQL "SET x=?, y=?, ..." )
		setValue(ps, i++, residenzariposo.getNomeresidenza() ) ; // "NomeResidenza" : java.lang.String
		setValue(ps, i++, residenzariposo.getNumeroposti() ) ; // "NumeroPosti" : java.lang.Integer
		setValue(ps, i++, residenzariposo.getIndirizzo() ) ; // "Indirizzo" : java.lang.String
		setValue(ps, i++, residenzariposo.getComune() ) ; // "Comune" : java.lang.String
		setValue(ps, i++, residenzariposo.getCap() ) ; // "Cap" : java.lang.String
		//--- Set PRIMARY KEY from bean to PreparedStatement ( SQL "WHERE key=?, ..." )
		setValue(ps, i++, residenzariposo.getId() ) ; // "id" : java.lang.Long
	}

}
