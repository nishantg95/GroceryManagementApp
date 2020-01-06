package com.nishant.services;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.nishant.daos.ItemDao;
import com.nishant.views.ItemView;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDao itemDao;

	private static final String REST_SERVICE_URI = "http://localhost:8080/GroceryManagementApp/data";
	private static final Logger LOGGER = LogManager.getLogger(ItemServiceImpl.class);

	@Override
	public Integer deleteItemById(Integer id) {
		return this.itemDao.deleteItemById(id);
	}

	@Override
	public List<ItemView> findAllItems() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ItemView[]> response = restTemplate.getForEntity(REST_SERVICE_URI + "/listAllItems",
				ItemView[].class);
		ItemView[] items = response.getBody();
		List<ItemView> itemsList = Arrays.asList(items);
		LOGGER.debug("REST GET= " + itemsList.toString());
		return itemsList;
	}

	@Override
	public ItemView findById(Integer id) {
		return null;
//		return this.itemDao.findById(id);
	}

	@Override
	public ItemView findByName(String name) {
		return null;
//		return this.itemDao.findByName(name);
	}

	@Override
	public Boolean isItemExist(ItemView itemView) {
		return null;
//		return this.itemDao.isItemExist(itemView);
	}

	@Override
	public ItemView saveItem(ItemView itemView) {
		return null;
//		return this.itemDao.saveItem(itemView);
	}

	@Override
	public ItemView updateItem(ItemView itemView) {
		return null;
//		return this.itemDao.updateItem(itemView);
	}

}
