package org.stockwatcher.data;

/**
 * Exception class that insulates callers of DAO methods from having to deal
 * with exceptions that are specific to the underlying database.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}
}