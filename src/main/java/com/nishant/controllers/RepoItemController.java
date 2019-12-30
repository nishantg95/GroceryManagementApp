/**
 *
 */
package com.nishant.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.nishant.entities.RepoItemEntity;
import com.nishant.managers.RepoItemManager;

/**
 * @author nishant.b.grover
 *
 */
@Controller
@RequestMapping(value = "/repo")
public class RepoItemController {

	private static final Logger LOGGER = LogManager.getLogger(RepoItemController.class);

	@Autowired
	private RepoItemManager repoItemManager;

	/***
	 * <p>
	 * This function returns a ModelAndView object to map Async request
	 * </p>
	 *
	 * @return
	 */
	@RequestMapping(value = "/addRepoItemForm", method = RequestMethod.GET)
	public ModelAndView addRepoItemForm() {
		return new ModelAndView("addRepoItemForm", "repoItem", new RepoItemEntity());
	}

	@RequestMapping(value = "/listAllRepoItems", method = RequestMethod.GET)
	public ResponseEntity<List<RepoItemEntity>> listAllRepoItems() {
		List<RepoItemEntity> repoItems = this.repoItemManager.findAllRepoItems();
		LOGGER.debug(String.valueOf(repoItems.size()));
		return new ResponseEntity<>(repoItems, HttpStatus.OK);
	}

	/***
	 * <p>
	 * This function receives a ItemEntity object
	 * </p>
	 *
	 * @param item
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/viewRepoItems", method = RequestMethod.POST)
	public View viewRepoItems(@Valid @ModelAttribute("repoItem") RepoItemEntity repoItem) {
		this.repoItemManager.saveItem(repoItem);
		return new RedirectView("viewRepoItems");
	}

	@RequestMapping(value = "/viewRepoItems", method = RequestMethod.GET)
	public String viewRepoItemsGet() {
		return "viewRepoItems";
	}
}
