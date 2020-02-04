package com.nishant.interfaces;

import java.util.Date;

public interface ItemInterface {

	Date getExpiryDate();

	String getFreezerDateString();

	Integer getId();

	String getLongevity();

	String getName();

	String getpantryDateString();

	Date getPurchaseDate();

	String getRefrigerateDateString();

	String getStorageState();

	void setExpiryDate(Date expiryDate);

	void setFreezerDateString(String freezerDateString);

	void setId(Integer id);

	void setLongevity(String longevity);

	void setName(String name);

	void setpantryDateString(String pantryDateString);

	void setPurchaseDate(Date purchaseDate);

	void setRefrigerateDateString(String refrigerateDateString);

	void setStorageState(String storageState);

}
