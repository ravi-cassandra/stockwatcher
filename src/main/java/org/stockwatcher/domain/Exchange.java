package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.Currency;

/**
 * A domain class that represents a service where investors can buy and sell 
 * shares of listed stocks. Each stock is traded on a single exchange.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class Exchange implements Comparable<Exchange> {
	private String exchangeId;
	private String name;
	private Currency currency;
	private boolean active;

	public Exchange(String exchangeId, String name, Currency currency) {
		this(exchangeId, name, currency, true);
	}

	public Exchange(String exchangeId, String name, Currency currency, 
		boolean active) {
		this.exchangeId = exchangeId;
		this.name = name;
		this.currency = currency;
		this.active = active;
	}

	public String getId() {
		return exchangeId;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public String getName() {
		return name;
	}

	public Currency getCurrency() {
		return currency;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public int compareTo(Exchange other) {
		return exchangeId.compareTo(other.exchangeId);
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