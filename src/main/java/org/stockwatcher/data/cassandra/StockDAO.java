package org.stockwatcher.data.cassandra;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.data.DAOException;
import org.stockwatcher.data.StockCriteria;
import org.stockwatcher.domain.Comment;
import org.stockwatcher.domain.Exchange;
import org.stockwatcher.domain.Industry;
import org.stockwatcher.domain.Stock;
import org.stockwatcher.domain.Trade;

/**
 * Cassandra-specific DAO interface that provides methods for reading 
 * exchanges, industries, stocks and trades.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface StockDAO extends org.stockwatcher.data.StockDAO {
	Stock getStockBySymbol(StatementOptions options, String symbol) throws DAOException;
	SortedSet<Industry> getIndustries(StatementOptions options) throws DAOException;
	SortedSet<Exchange> getExchanges(StatementOptions options) throws DAOException;
	SortedSet<Stock> findStocks(StatementOptions options, StockCriteria criteria) throws DAOException;
	SortedSet<Trade> getTradesBySymbolAndDate(StatementOptions options, String symbol, Date tradeDate) throws DAOException;
	Map<String, BigDecimal> getCurrentPriceForSymbols(StatementOptions options, String... symbols) throws DAOException;
	BigDecimal getLastClosePriceForSymbol(StatementOptions options, String symbol) throws DAOException;
	List<Stock> getMostWatchedStocks(StatementOptions options, int limit) throws DAOException;
	SortedSet<Comment> getStockCommentsBySymbol(StatementOptions options, String symbol, int limit) throws DAOException;
	void insertStockComment(StatementOptions options, String symbol, Comment comment) throws DAOException;
	void deleteStockComment(StatementOptions options, String symbol, UUID userId, UUID commentId) throws DAOException;
	void incrementStockViewCount(StatementOptions options, String symbol) throws DAOException;
}