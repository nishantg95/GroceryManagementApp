/**
 *
 */
package com.nishant.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.nishant.daos.RepoItemDao;
import com.nishant.entities.RepoItemEntity;

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

	@Autowired
	private RepoItemDao repoItemDao;

	@Override
	public List<RepoItemEntity> findAllRepoItems() {
		return this.repoItemDao.findAllRepoItems();
	}

	@Override
	public RepoItemEntity saveRepoItem(RepoItemEntity repoItem) {

		HttpEntity<?> httpEntity = new HttpEntity<Object>(repoItem, new HttpHeaders());
		String url = String.join("", REST_SERVICE_URI, "/addRepoItem/");
		ResponseEntity<RepoItemEntity> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
				RepoItemEntity.class);
		LOGGER.debug("Server status for creation of item " + repoItem.getrId() + ": " + responseEntity.getStatusCode());
		repoItem = responseEntity.getBody();
		return repoItem;
	}

}
