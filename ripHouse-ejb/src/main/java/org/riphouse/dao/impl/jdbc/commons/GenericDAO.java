package org.riphouse.dao.impl.jdbc.commons;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericDAO<T> {

	private Logger logger = LoggerFactory.getLogger(GenericDAO.class);

	private final static int INITIAL_POSITION = 1;
	private final DataSource dataSource;

	protected GenericDAO() {
		super();
		this.dataSource = DataSourceProvider.getDataSource();
	}

	protected abstract String getSqlSelect();

	protected abstract String getSqlInsert();

	protected abstract String getSqlUpdate();

	protected abstract String getSqlDelete();

	protected abstract String getSqlCount();

	protected abstract String getSqlCountAll();

	protected abstract void setValuesForPrimaryKey(PreparedStatement ps, int i, T bean) throws SQLException;

	protected abstract void setValuesForInsert(PreparedStatement ps, int i, T bean) throws SQLException;

	protected abstract void setValuesForUpdate(PreparedStatement ps, int i, T bean) throws SQLException;

	protected abstract T populateBean(ResultSet rs, T bean) throws SQLException;

	protected Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	protected void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logger.warn("I can't close connection: {}", e.getMessage(), e);
			}
		}
	}

	protected boolean doSelect(T bean) {

		boolean result = false;
		if (logger.isDebugEnabled()) logger.debug(getSqlSelect());

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlSelect())) {
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					populateBean(rs, bean);
					result = true;
				} else {
					result = false;
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	protected void doInsert(T bean) {

		if (logger.isDebugEnabled()) logger.debug(getSqlInsert());

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlInsert())) {
			setValuesForInsert(ps, INITIAL_POSITION, bean);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	protected Long doInsertAutoIncr(T bean) {
		Long generatedKey = 0L;
		
		if (logger.isDebugEnabled()) logger.debug(getSqlInsert());
		
		try (Connection conn = getConnection(); 
				PreparedStatement ps = conn.prepareStatement(getSqlInsert(), PreparedStatement.RETURN_GENERATED_KEYS)) {
			setValuesForInsert(ps, INITIAL_POSITION, bean);
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				if (rs.next()) {
					generatedKey = rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return generatedKey;
	}

	protected int doUpdate(T bean) {
		int result = 0;
		
		if (logger.isDebugEnabled()) logger.debug(getSqlUpdate());
		
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlUpdate())) {
			setValuesForUpdate(ps, INITIAL_POSITION, bean);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	protected int doDelete(T bean) {
		int result = 0;
		
		if (logger.isDebugEnabled()) logger.debug(getSqlDelete());
		
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlDelete())) {
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	protected boolean doExists(T bean) {
		long result = 0;

		if (logger.isDebugEnabled()) logger.debug(getSqlCount());

		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlCount())) {
			setValuesForPrimaryKey(ps, INITIAL_POSITION, bean);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = rs.getLong(1);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result > 0;
	}

	protected long doCountAll() {
		long result = 0;
		
		if (logger.isDebugEnabled()) logger.debug(getSqlCountAll());
		
		try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(getSqlCountAll()); ResultSet rs = ps.executeQuery()) {
			if (rs.next()) {
				result = rs.getLong(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
		return result;
	}

	protected void setValue(PreparedStatement ps, int i, String value) throws SQLException {
		ps.setString(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, java.util.Date value) throws SQLException {
		if (value != null) {
			ps.setDate(i, new java.sql.Date(value.getTime())); // Convert util.Date to sql.Date
		} else {
			ps.setNull(i, java.sql.Types.DATE);
		}
	}

	protected void setValue(PreparedStatement ps, int i, java.sql.Date value) throws SQLException {
		ps.setDate(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, java.sql.Time value) throws SQLException {
		ps.setTime(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, java.sql.Timestamp value) throws SQLException {
		ps.setTimestamp(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, Byte value) throws SQLException {
		if (value != null) {
			ps.setByte(i, value.byteValue());
		} else {
			ps.setNull(i, java.sql.Types.TINYINT);
		}
	}

	protected void setValue(PreparedStatement ps, int i, Short value) throws SQLException {
		if (value != null) {
			ps.setShort(i, value.shortValue());
		} else {
			ps.setNull(i, java.sql.Types.SMALLINT);
		}
	}
	
	protected void setValue(PreparedStatement ps, int i, Integer value) throws SQLException {
		if (value != null) {
			ps.setInt(i, value.intValue());
		} else {
			ps.setNull(i, java.sql.Types.INTEGER);
		}
	}

	protected void setValue(PreparedStatement ps, int i, Long value) throws SQLException {
		if (value != null) {
			ps.setLong(i, value.longValue());
		} else {
			ps.setNull(i, java.sql.Types.BIGINT); // JDBC : "BIGINT" => getLong/setLong
		}
	}

	protected void setValue(PreparedStatement ps, int i, Float value) throws SQLException {
		if (value != null) {
			ps.setFloat(i, value.floatValue());
		} else {
			ps.setNull(i, java.sql.Types.FLOAT);
		}
	}

	protected void setValue(PreparedStatement ps, int i, Double value) throws SQLException {
		if (value != null) {
			ps.setDouble(i, value.doubleValue());
		} else {
			ps.setNull(i, java.sql.Types.DOUBLE);
		}
	}

	protected void setValue(PreparedStatement ps, int i, BigDecimal value) throws SQLException {
		ps.setBigDecimal(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, byte[] value) throws SQLException {
		ps.setBytes(i, value);
	}

	protected void setValue(PreparedStatement ps, int i, Boolean value) throws SQLException {
		ps.setBoolean(i, value);
	}

}
