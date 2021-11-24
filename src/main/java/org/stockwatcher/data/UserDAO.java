package org.stockwatcher.data;

import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.domain.User;

/**
 * DAO interface that provides methods for reading and updating users.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface UserDAO {
	SortedSet<User> getUsers() throws DAOException;
	User getUser(UUID id) throws DAOException;
	User updateUser(User user) throws DAOException;
}