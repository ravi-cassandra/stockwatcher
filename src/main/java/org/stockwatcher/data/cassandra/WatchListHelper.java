package org.stockwatcher.data.cassandra;

import java.util.Date;

import org.stockwatcher.domain.Stock;
import org.stockwatcher.domain.WatchList;
import org.stockwatcher.domain.WatchList.Visibility;
import org.stockwatcher.domain.WatchListItem;

import com.datastax.driver.core.Row;
import com.datastax.driver.core.utils.UUIDs;

/**
 * Utility class that provides a static method for watch lists and 
 * watch list items from data returned by a query.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public final class WatchListHelper {
	private WatchListHelper() {
		// prevents instantiation
	}

	public static WatchList createWatchList(Row row, int itemCount) {
		WatchList watchList = new WatchList();
		watchList.setId(row.getUUID("watchlist_id"));
		watchList.setUserId(row.getUUID("user_id"));
		watchList.setDisplayName(row.getString("display_name"));
		watchList.setVisibility(Visibility.valueOf(row.getString("visibility")));
		watchList.setActive(row.getBool("active"));
		watchList.setCreated(new Date(UUIDs.unixTimestamp(watchList.getId())));
		watchList.setUpdated(row.getDate("updated"));
		watchList.setItemCount(itemCount);
		return watchList;
	}

	public static WatchListItem createWatchListItem(Row row, Stock stock) {
		WatchListItem item = new WatchListItem(stock);
		item.setWatchListId(row.getUUID("watchlist_id"));
		item.setCreated(row.getDate("created"));
		item.setStartPrice(row.getDecimal("start_price"));
		return item;
	}
}