/**
 *
 */
package com.nishant.views;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nishant.entities.ItemEntity;
import com.nishant.interfaces.ItemInterface;

/**
 * <p>
 * This class implements the itemModel interface defined in
 * com.nishant.interfaces. *
 * </p>
 *
 * @author nishant.b.grover
 *
 */
public class ItemView implements ItemInterface {

	private Integer id;

	private String name;

	private String longevity;

	private String storageState;

	// Server is running in CST timezone, better approach- custom JSON...
	// ... Serializer/Deserializer that gets/sets current jvm timezone
	@JsonFormat(pattern = "MM-dd-yyyy", timezone = "CST")
	private Date purchaseDate;

	@JsonFormat(pattern = "MM-dd-yyyy", timezone = "CST")
	private Date expiryDate;

	private String refrigerateDateString;

	private String pantryDateString;

	private String freezerDateString;

	/**
	 *
	 */
	public ItemView() {
		super();
		// Default Constructor
	}

	/**
	 * This Constructor initializes itemView with the bean type ItemEntity (passed
	 * as a parameter)
	 */
	public ItemView(ItemEntity itemEntity) {
		BeanUtils.copyProperties(itemEntity, this, ItemInterface.class);
	}

	@Override
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	@Override
	public String getFreezerDateString() {
		return this.freezerDateString;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getLongevity() {
		return this.longevity;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPantyrDateString() {
		return this.pantryDateString;
	}

	@Override
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	@Override
	public String getRefrigerateDateString() {
		return this.refrigerateDateString;
	}

	@Override
	public String getStorageState() {
		return this.storageState;
	}

	@Override
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public void setFreezerDateString(String freezerDateString) {
		this.freezerDateString = freezerDateString;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setLongevity(String longevity) {
		this.longevity = longevity;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPantyrDateString(String pantryDateString) {
		this.pantryDateString = pantryDateString;
	}

	@Override
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public void setRefrigerateDateString(String refrigerateDateString) {
		this.refrigerateDateString = refrigerateDateString;
	}

	@Override
	public void setStorageState(String storageState) {
		this.storageState = storageState;
	}

	@Override
	public String toString() {
		return "ItemView [id=" + this.id + ", name=" + this.name + ", longevity=" + this.longevity + ", storageState="
				+ this.storageState + ", purchaseDate=" + this.purchaseDate + ", expiryDate=" + this.expiryDate
				+ ", refrigerateDateString=" + this.refrigerateDateString + ", pantryDateString="
				+ this.pantryDateString + ", freezerDateString=" + this.freezerDateString + "]";
	}

}
