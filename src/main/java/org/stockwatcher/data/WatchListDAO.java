package org.stockwatcher.data;

import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.domain.WatchList;
import org.stockwatcher.domain.WatchListItem;

/**
 * DAO interface that provides methods for creating, reading, updating and
 * deleting watch lists and watch list items. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface WatchListDAO {
	void insertWatchList(WatchList watchList) throws DAOException;
	void deleteWatchList(UUID id) throws DAOException;
	void updateWatchList(WatchList watchList) throws DAOException;
	WatchList getWatchList(UUID id) throws DAOException;
	SortedSet<WatchList> getWatchListsByUserId(UUID userId) throws DAOException;
	SortedSet<String> getWatchListStockSymbols(UUID id) throws DAOException;
	SortedSet<WatchListItem> getWatchListItems(UUID id) throws DAOException;
	void addWatchListStock(UUID id, String stockSymbol) throws DAOException;
	void removeWatchListStock(UUID id, String stockSymbol) throws DAOException;
	int getWatchCount(String symbol) throws DAOException;
	int getWatchListCountByUserId(UUID userId) throws DAOException;
}