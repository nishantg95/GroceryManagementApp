/**
 *
 */
package com.nishant.mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.testng.Assert.assertEquals;

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

import com.nishant.entities.ItemEntity;
import com.nishant.managers.ItemManagerImpl;
import com.nishant.services.ItemService;
import com.nishant.views.ItemView;

/**
 * @author nishant.b.grover
 *
 */
@Profile("test")
public class MockItemManagerTests {

	@Mock
	private ItemService ItemService;// service

	@InjectMocks
	private ItemManagerImpl itemManagerImpl;// manager

	@BeforeTest
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	private void testDeleteItemById() {
		Mockito.when(this.ItemService.deleteItemById(Mockito.anyInt())).thenReturn(1).thenReturn(0);
		int result = this.itemManagerImpl.deleteItemById(20);
		Assert.assertEquals(result, 1);
		result = this.itemManagerImpl.deleteItemById(-30);
		Assert.assertEquals(result, 0);
		Mockito.verify(this.ItemService, times(2)).deleteItemById(Mockito.anyInt());

	}

	@Test
	private void testFindAllItems() throws Exception {
		List<ItemEntity> expectedList = Arrays.asList(Mockito.mock(ItemEntity.class), Mockito.mock(ItemEntity.class));
		Mockito.when(this.ItemService.findAllItems()).thenReturn(expectedList);
		List<ItemView> resultList = this.itemManagerImpl.findAllItems();
		Assert.assertEquals(resultList.get(0).getName(), expectedList.get(0).getName());
		Mockito.verify(this.ItemService, times(1)).findAllItems();
	}

	@Test
	private void testFindById() {
		ItemEntity expectedItemEntity = mock(ItemEntity.class);
		Mockito.when(this.ItemService.findById(Mockito.anyInt())).thenReturn(expectedItemEntity);
		ItemView resultItemView = this.itemManagerImpl.findById(Mockito.anyInt());
		Assert.assertEquals(expectedItemEntity.getName(), resultItemView.getName());
		Mockito.verify(this.ItemService, times(1)).findById(Mockito.anyInt());
	}

	@Test
	private void testFindByName() {
		ItemEntity expectedItemEntity = mock(ItemEntity.class);
		Mockito.when(this.ItemService.findByName(Mockito.anyString())).thenReturn(new ItemEntity());
		ItemView resultItemView = this.itemManagerImpl.findByName(Mockito.anyString());
		Assert.assertEquals(expectedItemEntity.getName(), resultItemView.getName());
		Mockito.verify(this.ItemService, times(1)).findByName(Mockito.anyString());
	}

	@Test
	private void testIsItemExist() {
		ItemView expectedItemView = mock(ItemView.class);
		Mockito.when(this.ItemService.isItemExist(Mockito.any(ItemEntity.class))).thenReturn(Boolean.TRUE)
				.thenReturn(Boolean.FALSE);
		Boolean resultBoolean = this.itemManagerImpl.isItemExist(expectedItemView);
		Assert.assertTrue(resultBoolean);
		resultBoolean = this.itemManagerImpl.isItemExist(new ItemView());
		Assert.assertFalse(resultBoolean);
		Mockito.verify(this.ItemService, times(2)).isItemExist(Mockito.any(ItemEntity.class));
	}

	@Test
	private void testSaveItem() {	
		ItemEntity expectedItemEntity = mock(ItemEntity.class);
		Mockito.when(this.ItemService.saveItem(Mockito.any(ItemEntity.class))).thenReturn(expectedItemEntity);
		ItemView resultItemView = this.itemManagerImpl.saveItem(new ItemView());
		Assert.assertEquals(expectedItemEntity.getName(), resultItemView.getName());
		Mockito.verify(this.ItemService, times(1)).saveItem(Mockito.any(ItemEntity.class));

	}

	@Test
	private void testUpdateItem() {
		ItemView expectedItemView = mock(ItemView.class);
		Mockito.doAnswer((invocation) -> {
			final Object args[] = invocation.getArguments();
			assertEquals(expectedItemView.getName(), ((ItemEntity) args[0]).getName());

			return null;
		}).when(this.ItemService).updateItem(Mockito.any(ItemEntity.class));
		this.itemManagerImpl.updateItem(expectedItemView);
		Mockito.verify(this.ItemService, times(1)).updateItem(Mockito.any(ItemEntity.class));

	}

}
