package com.nishant.services;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
		return null;
//		return this.itemDao.deleteItemById(id);
	}

	@Override
	public List<ItemView> findAllItems() {
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

	@SuppressWarnings("unchecked")
	@Override
	public ItemView updateItem(ItemView itemView) {

		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("id", itemView.getId());
		String url = String.join("", REST_SERVICE_URI, "/updateItem/{id}");
		restTemplate.put(url, itemView, params);
//		String requestBody = new String();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		headers.setAccept((List<MediaType>) MediaType.APPLICATION_JSON_UTF8);
//		LOGGER.debug("requestBody: " + requestBody.toString());
//		HttpEntity<String> entity = new HttpEntity<String>(requestBody, headers);
//		LOGGER.debug("entity: " + entity.toString());
//		String url = String.join("", REST_SERVICE_URI, "/updateItem/", itemView.getId().toString());
//		LOGGER.debug(url);
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
//		// check the response, e.g. Location header, Status, and body
//		response.getHeaders().getLocation();
//		response.getStatusCode();
//		LOGGER.debug("response: " + response);
		return new ItemView();
	}

}
