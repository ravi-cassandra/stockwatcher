package org.stockwatcher.data.cassandra;

import java.util.Date;
import java.util.UUID;

import org.stockwatcher.domain.User;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;

/**
 * Utility class that provides a static method for creating user objects from
 * data returned by a query.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public final class UserHelper {
	private UserHelper() {
		// prevents instantiation
	}

	public static User createUser(Row row, int watchListCount) {
		User user = new User();
		UUID userId = row.getUUID("user_id");
		user.setId(userId);
		user.setFirstName(row.getString("first_name"));
		user.setLastName(row.getString("last_name"));
		user.setDisplayName(row.getString("display_name"));
		user.setPostalCode(row.getString("postal_code"));
		user.setEmailAddress(row.getString("email_address"));
		user.setActive(row.getBool("active"));
		user.setCreated(new Date(UUIDs.unixTimestamp(userId)));
		user.setUpdated(row.getDate("updated"));
		user.setWatchListCount(watchListCount);
		return user;
	}
}