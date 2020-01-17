package com.nishant.managers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nishant.entities.ItemEntity;
import com.nishant.interfaces.ItemInterface;
import com.nishant.services.ItemService;
import com.nishant.views.ItemView;

@Service
public class ItemManagerImpl implements ItemManager {

	private static final Logger LOGGER = LogManager.getLogger(ItemManagerImpl.class);

	@Autowired
	private ItemService itemService;

	@Override
	public Integer deleteItemById(Integer id) {
		Integer deletedCountManager = this.itemService.deleteItemById(id);
		LOGGER.debug("{} item(s) deleted successfully = " + deletedCountManager);
		return deletedCountManager;
	}

	@Override
	public List<ItemView> findAllItems() {
		List<ItemView> itemViewList = new ArrayList<>();
		List<ItemEntity> itemEntityList = this.itemService.findAllItems();
		for (ItemEntity itemEntity : itemEntityList) {
			ItemView itemView = new ItemView(itemEntity);
			itemViewList.add(itemView);
		}
		return itemViewList;
	}

	@Override
	public ItemView findById(Integer id) {
		ItemEntity itemEntity = this.itemService.findById(id);
		ItemView itemView = new ItemView(itemEntity);
		return itemView;
	}

	@Override
	public ItemView findByName(String name) {
		ItemEntity itemEntity = this.itemService.findByName(name);
		ItemView itemView = new ItemView(itemEntity);
		return itemView;
	}

	@Override
	public Boolean isItemExist(ItemView item) {
		ItemEntity itemEntity = new ItemEntity(item);
		return this.itemService.isItemExist(itemEntity);
	}

	@Override
	public ItemView saveItem(ItemView item) {
		ItemEntity itemEntity = new ItemEntity(item);
		itemEntity = this.itemService.saveItem(itemEntity);
		if (itemEntity.getId() != null) {
			LOGGER.debug("Following Item was saved successfully: {}", item.toString());
		} else {
			LOGGER.debug("Following Item save failed: {}", item.toString());
		}
		BeanUtils.copyProperties(itemEntity, item, ItemInterface.class);
		return item;
	}

	@Override
	public void updateItem(ItemView item) {
		ItemEntity itemEntity = new ItemEntity(item);
		itemEntity = this.itemService.updateItem(itemEntity);
	}

}
