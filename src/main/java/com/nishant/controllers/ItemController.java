package com.nishant.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nishant.managers.ItemManager;
import com.nishant.views.ItemView;

/***
 * <p>
 * This Spring controller sets URL's to perform CRUD operations on /data URL for
 * item model
 * </p>
 *
 * @author nishant.b.grover
 *
 */
@RestController
@RequestMapping(value = "/GroceryManagementApp/data")
public class ItemController {

	private static final Logger LOGGER = LogManager.getLogger(ItemController.class);

	@Autowired
	private ItemManager itemManager;

	/***
	 * <p>
	 * Performs Create operation for Item Entity
	 * </p>
	 *
	 * @param itemView
	 * @param ucBuilder
	 * @return ResponseEntity
	 */

	@RequestMapping(value = "/createItem", method = RequestMethod.POST)
	public ResponseEntity<Void> createItem(@RequestBody ItemView itemView) {
		LOGGER.debug("Creating Item " + itemView.getName());

		if (this.itemManager.isItemExist(itemView)) {
			LOGGER.debug("A Item with name " + itemView.getName() + " already exist");
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		}

		this.itemManager.saveItem(itemView);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/***
	 * <p>
	 * Performs Delete operation for Item Entity for the specified ID supplied by
	 * PathVariable
	 * </p>
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteItem/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ItemView> deleteItem(@PathVariable("id") Integer id) {
		LOGGER.debug("Fetching & Deleting Item with id " + id);

		ItemView itemView = this.itemManager.findById(id);
		if (itemView == null) {
			LOGGER.debug("Unable to delete. Item with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		this.itemManager.deleteItemById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/***
	 * <p>
	 * Fetches all objects as an array list for for the item entity
	 * </p>
	 *
	 * @param header
	 * @return
	 */
	@RequestMapping(value = "/listAllItems", method = RequestMethod.GET)
	public ResponseEntity<List<ItemView>> listAllItems(@RequestHeader HttpHeaders header) {
		LOGGER.debug(header.get(HttpHeaders.USER_AGENT).toString());
		List<ItemView> items = this.itemManager.findAllItems();
		LOGGER.debug("fetched " + items.size() + " items.");
		if (items.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(items, HttpStatus.OK);
	}

	/***
	 * Performs Update operation for Item Entity for the specified ID supplied by
	 * PathVariable
	 *
	 * @param id
	 * @param itemView
	 * @return
	 */
	@RequestMapping(value = "/updateItem/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ItemView> updateItem(@PathVariable("id") Integer id, @RequestBody ItemView itemView) {
		LOGGER.info("Updating Item " + id);

		ItemView currentItem = this.itemManager.findById(id);

		if (currentItem == null) {
			LOGGER.debug("Item with id " + id + " not found");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		this.itemManager.updateItem(itemView);
		return new ResponseEntity<>(itemView, HttpStatus.OK);
	}

}
