package com.nishant.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
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

	public HttpEntity<?> createHttpEntity(Optional<ItemView> body) {
		HttpHeaders newHeaders = new HttpHeaders();
		newHeaders.set("Content-Type", "application/json");
		if (body.isPresent()) {
			HttpEntity<?> httpEntity = new HttpEntity<Object>(body.get(), newHeaders);
			return httpEntity;
		} else {
			HttpEntity<?> httpEntity = new HttpEntity<Object>(new String(), newHeaders);
			return httpEntity;
		}
	}

	@Override
	public Integer deleteItemById(Integer id) {
		HttpEntity<?> httpEntity = createHttpEntity(Optional.of(new ItemView()));
		String url = String.join("", REST_SERVICE_URI, "/deleteItem/" + id);
		ResponseEntity<Integer> responseEntity = restTemplate.exchange(url, HttpMethod.DELETE, httpEntity,
				Integer.class);
		LOGGER.debug("Server status: {} for deletion of item: {} ", responseEntity.getStatusCode(), id);
		return responseEntity.getBody();
	}

	@Override
	public List<ItemView> findAllItems() {
		HttpEntity<?> httpEntity = createHttpEntity(Optional.of(new ItemView()));
		String url = String.join("", REST_SERVICE_URI, "/listAllItems");
		ResponseEntity<List<ItemView>> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<ItemView>>() {
				});
		List<ItemView> items = response.getBody();
		LOGGER.debug("Server status: {} for fetching {} items", response.getStatusCode(), items.size());
		return items;
	}

	@Override
	public ItemView saveItem(ItemView itemView) {
		HttpEntity<?> httpEntity = createHttpEntity(Optional.of(itemView));
		String url = String.join("", REST_SERVICE_URI, "/createItem/");
		ResponseEntity<ItemView> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				ItemView.class);
		LOGGER.debug("Server status: {} for creation of item: {}", responseEntity.getStatusCode(), itemView.toString());
		itemView = responseEntity.getBody();
		return itemView;
	}

	@Override
	public ItemView updateItem(ItemView itemView) {
		HttpEntity<?> httpEntity = createHttpEntity(Optional.of(itemView));
		String url = String.join("", REST_SERVICE_URI, "/updateItem/" + itemView.getId());
		ResponseEntity<ItemView> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity,
				ItemView.class);
		LOGGER.debug("Server status: {} for update of item: {}", responseEntity.getStatusCode(), itemView.toString());
		itemView = responseEntity.getBody();
		return itemView;
	}

}
