package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.builder.ToStringStyle;

/**
 * A domain class that represents a list of stocks being watched by a user. 
 * Users of the StockWatcher application can create/destroy their own watch 
 * lists and add/remove stocks (represented by WatchListItems) from them.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class WatchList implements Comparable<WatchList> {
	public enum Visibility { PRIVATE, PROTECTED, PUBLIC }

	private UUID id;
	private UUID userId;
	private String displayName;
	private Visibility visibility = Visibility.PRIVATE;
	private Date created;
	private Date updated;
	private boolean active = true;
	private transient int itemCount;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		if(this.id != null) {
			throw new IllegalStateException("id has already been initialized");
		}
		this.id = id;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		if(this.userId != null) {
			throw new IllegalStateException("userId has already been initialized");
		}
		this.userId = userId;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	@Override
	public int compareTo(WatchList other) {
		return displayName.compareTo(other.displayName);
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