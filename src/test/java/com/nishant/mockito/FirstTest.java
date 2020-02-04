/**
 *
 */
package com.nishant.mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Profile;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.nishant.daos.ItemDao;
import com.nishant.entities.ItemEntity;
import com.nishant.services.ItemService;
import com.nishant.services.ItemServiceImpl;

/**
 * @author nishant.b.grover
 *
 */
@Profile("test")
public class FirstTest {

	@Mock
	private ItemDao mockItemDao;

	@InjectMocks
	private ItemService itemService = new ItemServiceImpl();

	@BeforeTest
	void setUp() throws Exception {
//		this.testItemEntityA.setName(UUID.randomUUID().toString());
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testDeleteById() {
		when(this.mockItemDao.deleteItemById(Mockito.anyInt())).thenReturn(1);
		int deletedCount = this.itemService.deleteItemById(23);
		Assert.assertEquals(deletedCount, 1);
		deletedCount = this.itemService.deleteItemById(-400);
		Assert.assertEquals(deletedCount, 1);
		verify(this.mockItemDao, times(2)).deleteItemById(Mockito.anyInt());

	}

	@Test
	void testFindAllItems() {
		when(this.mockItemDao.findAllItems()).thenReturn(null);
		List<ItemEntity> resultList = this.itemService.findAllItems();
		Assert.assertEquals(resultList, null);
		verify(this.mockItemDao, times(1)).findAllItems();

	}

}
