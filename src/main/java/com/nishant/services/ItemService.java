package com.nishant.services;

import java.util.List;

import com.nishant.views.ItemView;

public interface ItemService {

	Integer deleteItemById(Integer id);

	List<ItemView> findAllItems();

	ItemView findById(Integer id);

	ItemView findByName(String name);

	public Boolean isItemExist(ItemView item);

	ItemView saveItem(ItemView item);

	ItemView updateItem(ItemView item);

}
