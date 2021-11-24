package org.stockwatcher.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * DAO interface that provides methods for reading application properties.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface ApplicationPropertyDAO {
	boolean getBoolean(String propertyName) throws DAOException;
	int getInt(String propertyName) throws DAOException;
	long getLong(String propertyName) throws DAOException;
	Date getDate(String propertyName) throws DAOException;
	float getFloat(String propertyName) throws DAOException;
	double getDouble(String propertyName) throws DAOException;
	BigDecimal getDecimal(String propertyName) throws DAOException;
	UUID getUUID(String propertyName) throws DAOException;
	String getString(String propertyName) throws DAOException;
}