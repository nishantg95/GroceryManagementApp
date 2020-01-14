package com.nishant.managers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.services.ItemService;
import com.nishant.views.ItemView;

@Service
public class ItemManagerImpl implements ItemManager {

	private static final Logger LOGGER = LogManager.getLogger(ItemManagerImpl.class);

	@Autowired
	private ItemService itemService;

	@Override
	public void deleteItemById(Integer id) {
		Integer deletedCountManager = this.itemService.deleteItemById(id);
		LOGGER.debug("Number of items deleted successfully = " + deletedCountManager);
	}

	@Override
	public List<ItemView> findAllItems() {
		List<ItemView> itemViewList = this.itemService.findAllItems();
		LOGGER.debug("Returning all items fetched");
		return itemViewList;
	}

	@Override
	public void saveItem(ItemView item) {
		item = this.itemService.saveItem(item);
		if (item.getId() != null) {
			LOGGER.debug("Following Item was saved successfully" + item);
		} else {
			LOGGER.debug("Following Item saved failed" + item);
		}

	}

	@Override
	public void updateItem(ItemView item) {
		item = this.itemService.updateItem(item);
	}

}
