package com.nishant.managers;

import java.util.List;

import com.nishant.views.ItemView;

public interface ItemManager {

	void deleteItemById(Integer id);

	List<ItemView> findAllItems();

	void saveItem(ItemView item);

	void updateItem(ItemView item);

}