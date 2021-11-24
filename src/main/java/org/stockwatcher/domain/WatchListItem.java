package org.stockwatcher.domain;

import static java.math.BigDecimal.ZERO;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A domain class that represents a stock on a specific watch list. Each watch
 * list item captures the stock symbol and current price at the time it is 
 * added to the watch list. Users can add/remove watch list items from their
 * own watch lists.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class WatchListItem extends Stock {
	private UUID watchListId;
	private BigDecimal startPrice;
	private Date created;

	public WatchListItem(Stock stock) {
		super(stock);
	}

	public UUID getWatchListId() {
		return watchListId;
	}

	public void setWatchListId(UUID watchListId) {
		if(this.watchListId != null) {
			throw new IllegalStateException("watchListId has already been initialized");
		}
		this.watchListId = watchListId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		if(this.created != null) {
			throw new IllegalStateException("created has already been initialized");
		}
		this.created = created;
	}

	public BigDecimal getStartPrice() {
		return startPrice;
	}

	public void setStartPrice(BigDecimal startPrice) {
		if(this.startPrice != null) {
			throw new IllegalStateException("startPrice has already been initialized");
		}
		this.startPrice = startPrice;
	}

	public double getRateOfReturn() {
		if(startPrice == null || startPrice.equals(ZERO)) {
			throw new IllegalStateException("startPrice is null or zero");
		}
		BigDecimal currentPrice = getCurrentPrice();
		if(currentPrice == null || currentPrice.equals(ZERO)) {
			throw new IllegalStateException("currentPrice is null or zero");
		}
		return currentPrice.subtract(startPrice).doubleValue() /
			startPrice.doubleValue();
	}

	@Override
	public int compareTo(Stock other) {
		if(other instanceof WatchListItem) {
			int stockComparison = super.compareTo(other);
			WatchListItem otherItem = (WatchListItem)other;
			return stockComparison != 0 ? stockComparison : 
				watchListId.compareTo(otherItem.watchListId);
		} else {
			throw new IllegalArgumentException("argument is wrong type: " + 
				other.getClass().getName());
		}
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