package com.nishant.services;

import java.util.List;

import com.nishant.views.ItemView;

public interface ItemService {

	Integer deleteItemById(Integer id);

	List<ItemView> findAllItems();

	ItemView saveItem(ItemView item);

	ItemView updateItem(ItemView item);

}
