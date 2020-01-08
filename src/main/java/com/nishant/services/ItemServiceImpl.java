package com.nishant.services;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.nishant.views.ItemView;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	private static RestTemplate restTemplate = new RestTemplate();
	private static final String REST_SERVICE_URI = "http://localhost:8080/GroceryManagementApp/data";
	private static final Logger LOGGER = LogManager.getLogger(ItemServiceImpl.class);

	@Override
	public Integer deleteItemById(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(new String(), headers);
		String url = String.join("", REST_SERVICE_URI, "/deleteItem/" + id);
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity,
				Integer.class);
		LOGGER.debug("Server status for deletion of item " + id + ": " + responseEntity.getStatusCode());
		return responseEntity.getBody();
//		return this.itemDao.deleteItemById(id);
	}

	@Override
	public List<ItemView> findAllItems() {
		ResponseEntity<ItemView[]> response = restTemplate.getForEntity(REST_SERVICE_URI + "/listAllItems",
				ItemView[].class);
		ItemView[] items = response.getBody();
		List<ItemView> itemsList = Arrays.asList(items);
		LOGGER.debug("REST GET= fetched " + itemsList.size() + " items");
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
		HttpEntity<?> httpEntity = new HttpEntity<Object>(itemView, new HttpHeaders());
		String url = String.join("", REST_SERVICE_URI, "/createItem/");
		ResponseEntity<ItemView> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				ItemView.class);
		LOGGER.debug("Server status for creation of item " + itemView.getId() + ": " + responseEntity.getStatusCode());
		itemView = responseEntity.getBody();
		return itemView;
//		return this.itemDao.saveItem(itemView);
	}

	@Override
	public ItemView updateItem(ItemView itemView) {

//		Map<String, Integer> params = new HashMap<String, Integer>();
//		params.put("id", itemView.getId());
//		String url = String.join("", REST_SERVICE_URI, "/updateItem/{id}");
//		restTemplate.put(url, itemView, params);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(itemView, headers);
		String url = String.join("", REST_SERVICE_URI, "/updateItem/" + itemView.getId());
		ResponseEntity<ItemView> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
				ItemView.class);
		LOGGER.debug("Server status for update of item " + itemView.getId() + ": " + responseEntity.getStatusCode());
		itemView = responseEntity.getBody();
		return itemView;
	}

}
