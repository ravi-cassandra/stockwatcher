package org.stockwatcher.data.cassandra;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.stockwatcher.data.DAOException;

/**
 * Cassandra-specific DAO interface that provides methods for reading 
 * application properties.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface ApplicationPropertyDAO extends org.stockwatcher.data.ApplicationPropertyDAO {
	boolean getBoolean(StatementOptions options, String propertyName) throws DAOException;
	int getInt(StatementOptions options, String propertyName) throws DAOException;
	long getLong(StatementOptions options, String propertyName) throws DAOException;
	Date getDate(StatementOptions options, String propertyName) throws DAOException;
	float getFloat(StatementOptions options, String propertyName) throws DAOException;
	double getDouble(StatementOptions options, String propertyName) throws DAOException;
	BigDecimal getDecimal(StatementOptions options, String propertyName) throws DAOException;
	UUID getUUID(StatementOptions options, String propertyName) throws DAOException;
	String getString(StatementOptions options, String propertyName) throws DAOException;
}