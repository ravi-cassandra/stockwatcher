package org.stockwatcher.domain;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

/**
 * A domain class that represents the sector that industries are categorized 
 * under. Each industry is categorized under a single sector. The source of 
 * this data is the Yahoo Finance site (http://finance.yahoo.com).
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class Sector implements Comparable<Sector> {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public Sector(Integer id, String name) {
		if(id == null) {
			throw new IllegalArgumentException("id is null");
		}
		if(name == null || name.length() == 0) {
			throw new IllegalArgumentException("name is null or zero length");
		}
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Sector other) {
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