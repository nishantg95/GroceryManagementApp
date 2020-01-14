package com.nishant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * <p>
 * Controller class to bind models and views to URL's that do not
 * perform/request a Hibernate transaction. This controller is also responsible
 * for Asynchronous submission
 * </p>
 *
 * @author nishant.b.grover
 *
 */
@Controller
public class AppViewController {

	/***
	 * <p>
	 * This function returns the name of jsp view to be binded for index page
	 * </p>
	 *
	 * @return
	 */
	@RequestMapping(value = { "", "/", "welcome" }, method = RequestMethod.GET)
	public String homePage() {
		return "welcome";
	}

	/***
	 * <p>
	 * Sets the URL and view for user inventory view
	 * </p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/inventory", method = RequestMethod.GET)
	public String userView() {
		return "inventory";
	}

}
