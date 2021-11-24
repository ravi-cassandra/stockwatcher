package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * A domain class that represents a transaction on a stock exchange. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class Trade implements Comparable<Trade> {
	private UUID id;
	private Date timestamp;
	private String exchangeId;
	private String stockSymbol;
	private BigDecimal sharePrice;
	private int shareQuantity;

	public Trade(UUID id, Date timestamp, String exchangeId, 
		String stockSymbol, BigDecimal sharePrice, int shareQuantity) {
		if(id == null) {
			throw new IllegalArgumentException("id is null");
		}
		this.id = id;
		if(timestamp == null) {
			throw new IllegalArgumentException("timestamp is null");
		}
		this.timestamp = timestamp;
		if(exchangeId == null || exchangeId.length() == 0) {
			throw new IllegalArgumentException("exchangeId is null or zero length");
		}
		this.exchangeId = exchangeId;
		if(stockSymbol == null || stockSymbol.length() == 0) {
			throw new IllegalArgumentException("stockSymbol is null or zero length");
		}
		this.stockSymbol = stockSymbol;
		if(sharePrice == null) {
			throw new IllegalArgumentException("sharePrice is null");
		}
		this.sharePrice = sharePrice;
		this.shareQuantity = shareQuantity;
	}

	public UUID getId() {
		return id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	public int getShareQuantity() {
		return shareQuantity;
	}

	@Override
	public int compareTo(Trade other) {
		return id.compareTo(other.id);
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
		return reflectionToString(this);
	}
}