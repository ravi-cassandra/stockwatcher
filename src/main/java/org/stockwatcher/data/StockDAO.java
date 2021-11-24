package org.stockwatcher.data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.UUID;

import org.stockwatcher.domain.Comment;
import org.stockwatcher.domain.Exchange;
import org.stockwatcher.domain.Industry;
import org.stockwatcher.domain.Stock;
import org.stockwatcher.domain.Trade;

/**
 * DAO interface that provides methods for reading stocks, industries, 
 * sectors and trades.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public interface StockDAO {
	Stock getStockBySymbol(String symbol) throws DAOException;
	SortedSet<Industry> getIndustries() throws DAOException;
	SortedSet<Exchange> getExchanges() throws DAOException;
	SortedSet<Stock> findStocks(StockCriteria criteria) throws DAOException;
	SortedSet<Trade> getTradesBySymbolAndDate(String symbol, Date tradeDate) throws DAOException;
	Map<String, BigDecimal> getCurrentPriceForSymbols(String... symbols) throws DAOException;
	BigDecimal getLastClosePriceForSymbol(String symbol) throws DAOException;
	List<Stock> getMostWatchedStocks(int limit) throws DAOException;
	SortedSet<Comment> getStockCommentsBySymbol(String symbol, int limit) throws DAOException;
	void insertStockComment(String symbol, Comment comment) throws DAOException;
	void deleteStockComment(String symbol, UUID userId, UUID commentId) throws DAOException;
	void incrementStockViewCount(String symbol) throws DAOException;
}