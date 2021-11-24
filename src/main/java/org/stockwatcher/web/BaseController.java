package org.stockwatcher.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.stockwatcher.data.UserDAO;
import org.stockwatcher.domain.User;

/**
 * Base controller class that provides common functionality for all controller
 * classes.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
public abstract class BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	@Autowired
	private UserDAO userDAO;

	protected User getUser(String userId) {
		User user = null;
		if(userId == null) {
			throw new IllegalArgumentException("userId is null or zero length");
		}
		try {
			UUID id = UUID.fromString(userId); 
			user = userDAO.getUser(id);
		} catch(Exception e) {
			LOGGER.debug("Invalid userId ({}) specified", userId);
		}
		return user;
	}

	protected String getRequestParameter(HttpServletRequest request, String name) {
		String parameter = request.getParameter(name);
		if(parameter == null || parameter.length() == 0) {
			throw new IllegalArgumentException(parameter + 
				" parameter is null or zero length");
		}
		return parameter;
	}
}