package org.stockwatcher.web.rss;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.stockwatcher.domain.Stock;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Item;

/**
 * View class that generates an RSS feed for the most watched stocks. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class MostWatchedStocksRssView extends BaseRssView {
	private String feedTitle;
	private String feedDescription;

	@Required
	public void setFeedTitle(String feedTitle) {
		this.feedTitle = feedTitle;
	}

	@Required
	public void setFeedDescription(String feedDescription) {
		this.feedDescription = feedDescription;
	}

	@Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
    	HttpServletRequest request) {
    	feed.setTitle(feedTitle);
    	feed.setDescription(feedDescription);
    	feed.setGenerator(feedGenerator);
    	feed.setLink(feedLink);
    }

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Stock> stocks = (List<Stock>)model.get("stocks");
		if(stocks == null) {
			throw new RuntimeException("stocks not found in the model");
		}
		List<Item> items = new ArrayList<Item>();
		Date now = new Date();
		for(Stock stock : stocks) {
			String symbol = stock.getSymbol();
            Item item = new Item();
            item.setTitle(format("%s $%8.2f", symbol, 
            	stock.getCurrentPrice().floatValue()));
            item.setAuthor(itemAuthor);
            item.setPubDate(now);
            item.setLink(getStockLink(request, symbol));
            item.setExpirationDate(now);
            items.add(item);
		}
		return items;
	}
}