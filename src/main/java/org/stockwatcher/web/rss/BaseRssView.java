package org.stockwatcher.web.rss;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

/**
 * Base view class that provides common functionality used by all RSS view 
 * classes. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public abstract class BaseRssView extends AbstractRssFeedView {
	protected String feedGenerator;
	protected String feedLink;
	protected String itemAuthor;

	public BaseRssView() {
		super();
	}

	@Required
	public void setFeedGenerator(String feedGenerator) {
		this.feedGenerator = feedGenerator;
	}

	@Required
	public void setFeedLink(String feedLink) {
	    this.feedLink = feedLink;
	}

	@Required
	public void setItemAuthor(String itemAuthor) {
		this.itemAuthor = itemAuthor;
	}

	protected String getStockLink(HttpServletRequest request, String symbol) {
		StringBuilder link = new StringBuilder(request.getScheme());
		link.append("://");
		link.append(request.getServerName());
		link.append(":");
		link.append(request.getServerPort());
		link.append(request.getContextPath());
		link.append("/main/stocks/");
		link.append(symbol);
		return link.toString();
	}
}