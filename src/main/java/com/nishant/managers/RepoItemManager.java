/**
 *
 */
package com.nishant.managers;

import java.util.List;

import com.nishant.views.RepoItemView;

/**
 * @author nishant.b.grover
 *
 */
public interface RepoItemManager {

	List<RepoItemView> findAllRepoItems();

	/**
	 * @param repoItem
	 */
	void saveItem(RepoItemView repoItem);

}
