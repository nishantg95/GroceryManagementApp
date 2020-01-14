/**
 *
 */
package com.nishant.services;

import java.util.List;

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

import com.nishant.views.RepoItemView;

/**
 * @author nishant.b.grover
 *
 */
@Service
@Transactional
public class RepoItemServiceImpl implements RepoItemService {

	private static final String REST_SERVICE_URI = "http://localhost:8080/GroceryManagementApp/repo";
	private static RestTemplate restTemplate = new RestTemplate();
	private static final Logger LOGGER = LogManager.getLogger(RepoItemServiceImpl.class);

	@Override
	public List<RepoItemView> findAllRepoItems() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json");
		HttpEntity<?> httpEntity = new HttpEntity<Object>(new String(), headers);
		String url = String.join("", REST_SERVICE_URI, "/listAllRepoItems");
		ResponseEntity<List<RepoItemView>> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<RepoItemView>>() {
				});
		List<RepoItemView> repoItems = response.getBody();
		return repoItems;
	}

	@Override
	public RepoItemView saveRepoItem(RepoItemView repoItem) {

		HttpEntity<?> httpEntity = new HttpEntity<Object>(repoItem, new HttpHeaders());
		String url = String.join("", REST_SERVICE_URI, "/addRepoItem/");
		ResponseEntity<RepoItemView> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				RepoItemView.class);
		LOGGER.debug("Server status for creation of item " + repoItem.getrId() + ": " + responseEntity.getStatusCode());
		repoItem = responseEntity.getBody();
		return repoItem;
	}

}
