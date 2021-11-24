package org.stockwatcher.data.cassandra;

import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.data.DAOException;
import org.stockwatcher.domain.User;

/**
 * Cassandra-specific DAO interface that provides methods for reading 
 * and updating users.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface UserDAO extends org.stockwatcher.data.UserDAO {
	SortedSet<User> getUsers(StatementOptions options) throws DAOException;
	User getUser(StatementOptions options, UUID id) throws DAOException;
	User updateUser(StatementOptions options, User user) throws DAOException;
}