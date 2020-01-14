/**
 *
 */
package com.nishant.services;

import java.util.List;

import com.nishant.views.RepoItemView;

/**
 * @author nishant.b.grover
 *
 */
public interface RepoItemService {

	List<RepoItemView> findAllRepoItems();

	/**
	 * @param repoItem
	 * @return
	 */
	RepoItemView saveRepoItem(RepoItemView repoItem);

}
