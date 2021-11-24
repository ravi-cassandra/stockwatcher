package org.stockwatcher.task;

import static java.lang.Thread.sleep;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.stockwatcher.data.ApplicationProperties;
import org.stockwatcher.data.StockDAO;

@ManagedResource(description="Task to calculate various Stock statistics")
public class StockStatsTask {
	private static final Logger LOGGER = LoggerFactory.getLogger(StockStatsTask.class);

	private Date lastExecution = new Date(0);
	@Autowired
	private ApplicationProperties applicationProps;
	@Autowired
	private StockDAO stockDAO;

	@ManagedAttribute(description="Last time this task was executed")
	public Date getLastExecution() {
		return lastExecution;
	}

	public void calculateStockStats() {
		lastExecution = new Date();
		if(applicationProps.isTradingLive()) {
			try {
				sleep(5000);	// In case trading just started
				
				// TO-DO: Add code here to calculate some statistics
				
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}