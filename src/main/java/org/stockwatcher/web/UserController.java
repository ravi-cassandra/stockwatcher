package org.stockwatcher.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stockwatcher.data.UserDAO;
import org.stockwatcher.domain.User;

/**
 * Controller class for all User-related web requests. Uses DAO classes to 
 * interact with the database.
 * 
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserDAO dao;

	@RequestMapping(method=RequestMethod.GET)
	public String allUsers(Model model) {
		model.addAttribute("users", dao.getUsers());
		return "users";
	}

	@RequestMapping(value="/select/{userId}", method=RequestMethod.GET)
	public String selectUser(@PathVariable String userId, Model model, 
		HttpServletRequest request, HttpServletResponse response) {
		String view = "redirect:/main/users";
		// first we need to validate the user
		User user = getUser(userId);
		if(user != null) {
			LOGGER.info("User selected with id = {}", userId);
			request.getSession().setAttribute("user", user);
		} else {
			String path = request.getQueryString();
			view = path.substring(0, path.indexOf("/select/"));
		}
		return view;
	}
}