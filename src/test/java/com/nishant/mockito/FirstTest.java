/**
 *
 */
package com.nishant.mockito;

import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
public class FirstTest {

	@Mock
	private ItemService ItemService;// service

	@InjectMocks
	private ItemManagerImpl itemManagerImpl;// manager

	ItemEntity createMockItemEntity() {
		ItemEntity mockItemEntity = new ItemEntity();
		mockItemEntity.setId(new Random().nextInt(200 - 1) + 1);
		mockItemEntity.setName("Test Item_" + Calendar.getInstance().getTimeInMillis());
		mockItemEntity.setPurchaseDate(new Date());
		mockItemEntity.setStorageState("Pantry");
		mockItemEntity.setLongevity((new Random().nextInt(7 - 1) + 1) + " days");
		return mockItemEntity;

	}

	@BeforeTest
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

//	Integer deleteItemById(Integer id);
	@Test
	private void testDeleteItemById() {
		Mockito.when(this.ItemService.deleteItemById(Mockito.anyInt())).thenReturn(1).thenReturn(0);
		int result = this.itemManagerImpl.deleteItemById(20);
		Assert.assertEquals(result, 1);
		result = this.itemManagerImpl.deleteItemById(-30);
		Assert.assertEquals(result, 0);
		Mockito.verify(this.ItemService, times(2)).deleteItemById(Mockito.anyInt());

	}

//	List<ItemView> findAllItems();
	@Test
	private void testFindAllItems() {
		List<ItemEntity> expectedList = Arrays.asList(Mockito.mock(ItemEntity.class), Mockito.mock(ItemEntity.class));
		Mockito.when(this.ItemService.findAllItems()).thenReturn(expectedList);
		List<ItemView> resultList = this.itemManagerImpl.findAllItems();
		Assert.assertEquals(resultList.get(0).getName(), expectedList.get(0).getName());
		Mockito.verify(this.ItemService, times(1)).findAllItems();

	}

//	ItemView findById(Integer id);
	@Test
	private void testFindById() {
		Mockito.when(this.ItemService.findById(Mockito.anyInt())).thenReturn(new ItemEntity());

	}

//	ItemView findByName(String name);
	@Test
	private void testFindByName() {
		Mockito.when(this.ItemService.findByName(Mockito.anyString())).thenReturn(new ItemEntity());
	}

// Boolean isItemExist(ItemView item);
	@Test
	private void testIsItemExist() {
		Mockito.when(this.ItemService.isItemExist(Mockito.any(ItemEntity.class))).thenReturn(Boolean.TRUE)
				.thenReturn(Boolean.FALSE);

	}

//	ItemView saveItem(ItemView item);
	@Test
	private void testSaveItem() {
		Mockito.when(this.ItemService.saveItem(Mockito.any(ItemEntity.class))).thenReturn(new ItemEntity());

	}

//	void updateItem(ItemView item);
	@Test
	private void testUpdateItem() {
		Mockito.when(this.ItemService.updateItem(Mockito.any(ItemEntity.class)));

	}

//	@Test
//	void testFindAllItems() {
//		when(this.mockItemDao.findAllItems()).thenReturn(this.dummyListItems);
//		List<ItemEntity> resultList = this.itemService.findAllItems();
//		Assert.assertEquals(resultList, this.dummyListItems);
//		verify(this.mockItemDao, times(1)).findAllItems();
//
//	}

}
