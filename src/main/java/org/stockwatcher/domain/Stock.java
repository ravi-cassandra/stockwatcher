package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A domain class that represents a stock that is traded on an exchange. 
 * Stocks are identified by their symbol. The source of this data is the 
 * Yahoo Finance site (http://finance.yahoo.com).
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class Stock implements Comparable<Stock> {
	private String symbol;
	private String companyName;
	private String exchangeId;
	private Industry industry;
	private transient BigDecimal currentPrice;
	private transient Date priceUpdated;
	private boolean active;

	public Stock(String symbol, String companyName, String exchangeId, 
		Industry industry, BigDecimal currentPrice, Date priceUpdated) {
		this(symbol, companyName, exchangeId, industry, currentPrice, 
			priceUpdated, true);
	}

	public Stock(String symbol, String companyName, String exchangeId, 
		Industry industry, BigDecimal currentPrice, Date priceUpdated, 
		boolean active) {
		this.symbol = symbol;
		this.companyName = companyName;
		this.exchangeId = exchangeId;
		this.industry = industry;
		this.currentPrice = currentPrice;
		this.priceUpdated = priceUpdated;
		this.active = active;
	}

	/**
	 * Copy construcor
	 * @param stock
	 */
	protected Stock(Stock stock) {
		this(stock.getSymbol(), stock.companyName, stock.exchangeId, 
			stock.industry, stock.currentPrice, stock.priceUpdated, 
			stock.active);
	}

	public String getSymbol() {
		return symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public Industry getIndustry() {
		return industry;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public Date getPriceUpdated() {
		return priceUpdated;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public int compareTo(Stock other) {
		return symbol.compareTo(other.symbol);
	}

	@Override
	public boolean equals(Object other) {
		return reflectionEquals(this, other, false);
	}

	@Override
	public int hashCode() {
		return reflectionHashCode(this, false);
	}

	@Override
	public String toString() {
		return reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE, true);
	}
}