package org.stockwatcher.web.rss;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stockwatcher.domain.User;
import org.stockwatcher.domain.WatchList;
import org.stockwatcher.domain.WatchListItem;

import com.sun.syndication.feed.rss.Channel;
import com.sun.syndication.feed.rss.Description;
import com.sun.syndication.feed.rss.Item;

/**
 * View class that generates an RSS feed for a specific WatchList. 
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public class WatchListRssView extends BaseRssView {
	private static final DecimalFormat CHANGE_FORMAT = new DecimalFormat("0.0");
	private static final DateFormat CREATED_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	@Override
    protected void buildFeedMetadata(Map<String, Object> model, Channel feed,
    	HttpServletRequest request) {
    	WatchList watchList = (WatchList)model.get("watchList");
		if(watchList == null) {
			throw new RuntimeException("watchlist not found in the model");
		}
    	User user = (User)model.get("user");
		if(user == null) {
			throw new RuntimeException("user not found in the model");
		}
    	feed.setTitle("Watch List: " + watchList.getDisplayName());
    	StringBuilder description = new StringBuilder("Created by ");
    	description.append(user.getFirstName());
    	description.append(" ");
    	description.append(user.getLastName());
    	description.append(" (");
    	description.append(user.getDisplayName());
    	description.append(")");
    	feed.setDescription(description.toString());
    	feed.setGenerator(feedGenerator);
    	feed.setLink(feedLink);
    }

	@Override
	@SuppressWarnings("unchecked")
	protected List<Item> buildFeedItems(Map<String, Object> model,
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Item> feedItems = new ArrayList<Item>();
		SortedSet<WatchListItem> watchListItems = 
			(SortedSet<WatchListItem>)model.get("watchListItems");
		Date now = new Date();
		for(WatchListItem watchListItem : watchListItems) {
            Item feedItem = new Item();
            feedItem.setTitle(getTitle(watchListItem));
            feedItem.setAuthor(itemAuthor);
            feedItem.setPubDate(now);
            feedItem.setDescription(getDescription(watchListItem));
            feedItem.setLink(getStockLink(request, watchListItem.getSymbol()));
            feedItem.setExpirationDate(now);
            feedItems.add(feedItem);
		}
		return feedItems;
	}

	private String getTitle(WatchListItem item) {
		StringBuilder title = new StringBuilder(item.getCompanyName());
		title.append(" (");
		title.append(item.getSymbol());
		title.append(")");
		return title.toString();
	}

	private Description getDescription(WatchListItem item) {
        Description description = new Description();
        description.setType("text/html");
        StringBuilder html = new StringBuilder();
        html.append("<table>");
        html.append("<tr>");
        html.append("<td>Exchange:</td>");
        html.append("<td>");
        html.append(item.getExchangeId());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Industry:</td>");
        html.append("<td>");
        html.append(item.getIndustry().getName());
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Date Added:</td>");
        html.append("<td>");
        html.append(CREATED_FORMAT.format(item.getCreated()));
        html.append("</td>");
        html.append("</tr>");

        html.append("<tr>");
        html.append("<td>Start Price:</td>");
        html.append("<td>$&nbsp;");
        html.append(item.getStartPrice());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Current Price:</td>");
        html.append("<td>$&nbsp;");
        html.append(item.getCurrentPrice());
        html.append("</td>");
        html.append("</tr>");
        html.append("<tr>");
        html.append("<td>Change:</td>");
        html.append("<td>");
        html.append(CHANGE_FORMAT.format(item.getRateOfReturn()));
        html.append("&nbsp;%</td>");
        html.append("</tr>");
        html.append("</table");
        description.setValue(html.toString());
		return description;
	}
}