/**
 *
 */
package com.nishant.views;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@Override
	public String toString() {
		return "ItemView [id=" + id + ", name=" + name + ", longevity=" + longevity + ", storageState=" + storageState
				+ ", purchaseDate=" + purchaseDate + ", expiryDate=" + expiryDate + ", refrigerateDateString="
				+ refrigerateDateString + ", pantryDateString=" + pantryDateString + ", freezerDateString="
				+ freezerDateString + "]";
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
	public String getpantryDateString() {
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
	public void setpantryDateString(String pantryDateString) {
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

}