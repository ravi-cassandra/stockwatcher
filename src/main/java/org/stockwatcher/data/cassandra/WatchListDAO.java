package org.stockwatcher.data.cassandra;

import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.data.DAOException;
import org.stockwatcher.domain.WatchList;
import org.stockwatcher.domain.WatchListItem;

/**
 * Cassandra-specific DAO interface that provides methods for creating, 
 * reading, updating and deleting watch lists and watch list items. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface WatchListDAO extends org.stockwatcher.data.WatchListDAO {
	void insertWatchList(StatementOptions options, WatchList watchList) throws DAOException;
	void deleteWatchList(StatementOptions options, UUID id) throws DAOException;
	void updateWatchList(StatementOptions options, WatchList watchList) throws DAOException;
	WatchList getWatchList(StatementOptions options, UUID id) throws DAOException;
	SortedSet<WatchList> getWatchListsByUserId(StatementOptions options, UUID userId) throws DAOException;
	SortedSet<String> getWatchListStockSymbols(StatementOptions options, UUID id) throws DAOException;
	SortedSet<WatchListItem> getWatchListItems(StatementOptions options, UUID id) throws DAOException;
	void addWatchListStock(StatementOptions options, UUID id, String stockSymbol) throws DAOException;
	void removeWatchListStock(StatementOptions options, UUID id, String stockSymbol) throws DAOException;
	int getWatchCount(StatementOptions options, String symbol) throws DAOException;
	int getWatchListCountByUserId(StatementOptions options, UUID userId) throws DAOException;
}