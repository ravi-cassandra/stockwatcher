package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

/**
 * A domain class that represents the industry stocks are categorized under. 
 * Each stock is categorized under a single industry. The source of this data
 * is the Yahoo Finance site (http://finance.yahoo.com).
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class Industry implements Comparable<Industry> {
	private Integer id;
	private Sector sector;
	private String name;

	public Integer getId() {
		return id;
	}

	public Industry(Integer id, String name, Sector sector) {
		if(id == null) {
			throw new IllegalArgumentException("id is null");
		}
		if(name == null || name.length() == 0) {
			throw new IllegalArgumentException("name is null or zero length");
		}
		if(sector == null) {
			throw new IllegalArgumentException("sector is null");
		}
		this.id = id;
		this.name = name;
		this.sector = sector;
	}

	public Sector getSector() {
		return sector;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Industry other) {
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