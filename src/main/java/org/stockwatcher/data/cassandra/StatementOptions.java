package org.stockwatcher.data.cassandra;

import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import com.datastax.driver.core.policies.RetryPolicy;

/**
 * Class that encapsulates the options that can be specified when executing
 * a statement with the DataStax Java Driver.
 *  
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class StatementOptions {
	private boolean tracing;
	private ConsistencyLevel consistencyLevel = ConsistencyLevel.ONE;
	private RetryPolicy retryPolicy = DefaultRetryPolicy.INSTANCE;

	public StatementOptions() {
		// nothing here
	}

	public StatementOptions(boolean tracing) {
		this.tracing = tracing;
	}

	public StatementOptions(ConsistencyLevel consistencyLevel) {
		setConsistencyLevel(consistencyLevel);
	}

	public StatementOptions(RetryPolicy retryPolicy) {
		setRetryPolicy(retryPolicy);
	}

	public StatementOptions(ConsistencyLevel consistencyLevel, 
		RetryPolicy retryPolicy) {
		setConsistencyLevel(consistencyLevel);
		setRetryPolicy(retryPolicy);
	}

	public void enableTracing() {
		tracing = true;
	}

	public void disableTracing() {
		tracing = false;
	}

	public boolean isTracing() {
		return tracing;
	}

	public ConsistencyLevel getConsistencyLevel() {
		return consistencyLevel;
	}

	public void setConsistencyLevel(ConsistencyLevel consistencyLevel) {
		if(consistencyLevel == null) {
			throw new IllegalArgumentException("consistencyLevel is null");
		}
		this.consistencyLevel = consistencyLevel;
	}

	public RetryPolicy getRetryPolicy() {
		return retryPolicy;
	}

	public void setRetryPolicy(RetryPolicy retryPolicy) {
		if(retryPolicy == null) {
			throw new IllegalArgumentException("retryPolicy is null");
		}
		this.retryPolicy = retryPolicy;
	}
}