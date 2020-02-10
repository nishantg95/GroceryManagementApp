/**
 *
 */
package com.nishant.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nishant.entities.RepoItemEntity;
import com.nishant.managers.RepoItemManagerImpl;
import com.nishant.services.RepoItemService;

/**
 * @author nishant.b.grover
 *
 */
@Profile("test")
public class MockRepoItemManagerTests {

	@Mock
	private RepoItemService repoItemService;

	@InjectMocks
	private RepoItemManagerImpl repoItemManagerImpl;

	@BeforeTest
	private void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	private void testFindAllRepoItems() {
		List<RepoItemEntity> expectedList = Arrays.asList(Mockito.mock(RepoItemEntity.class),
				Mockito.mock(RepoItemEntity.class));
		Mockito.when(this.repoItemService.findAllRepoItems()).thenReturn(expectedList);
		List<RepoItemEntity> resultList = this.repoItemManagerImpl.findAllRepoItems();
		Assert.assertEquals(resultList, expectedList);
		Mockito.verify(this.repoItemService, times(1)).findAllRepoItems();

	}

	@Test
	private void testSaveItem() {
		RepoItemEntity expectedRepoItemEntity = mock(RepoItemEntity.class);
		Mockito.when(this.repoItemService.saveRepoItem(Mockito.any(RepoItemEntity.class)))
				.thenReturn(expectedRepoItemEntity);
		RepoItemEntity resultRepoItemEntity = this.repoItemManagerImpl.saveItem(new RepoItemEntity());
		Assert.assertEquals(expectedRepoItemEntity.getrName(), resultRepoItemEntity.getrName());// fails without Getter
		Mockito.verify(this.repoItemService, times(1)).saveRepoItem(Mockito.any(RepoItemEntity.class));
	}
}
