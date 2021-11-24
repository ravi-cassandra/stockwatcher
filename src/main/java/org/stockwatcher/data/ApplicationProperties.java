package org.stockwatcher.data;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Convenience class that provides methods for reading application properties.
 *  
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
@Component
public class ApplicationProperties {
	private static final String APP_VERSION = "1.0";

	@Autowired
	private ApplicationPropertyDAO dao;

	public Date getLastTradeDate() {
		return dao.getDate("last_trade_date");
	}

	public String getApplicationVersion() {
		return APP_VERSION;
	}

	public boolean isTradingLive() {
		return dao.getBoolean("trading_is_live");
	}
}