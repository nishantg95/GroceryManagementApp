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
//@JsonSerialize
public class ItemView implements ItemInterface {

	private Integer id;

	private String name;

	private String shelfLife;

	private String storageState;

	// Server is running in CST timezone, better approach- custom JSON...
	// ... Serializer/Deserializer that gets/sets current jvm timezone
	@JsonFormat(pattern = "MM-dd-yyyy", timezone = "CST")
	private Date purchaseDate;

	@JsonFormat(pattern = "MM-dd-yyyy", timezone = "CST")
	private Date expiryDate;

	/**
	 *
	 */
	public ItemView() {
		// Default Constructor
	}

	@Override
	public Date getExpiryDate() {
		return this.expiryDate;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	@Override
	public String getShelfLife() {
		return this.shelfLife;
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
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;

	}

	@Override
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	@Override
	public void setStorageState(String storageState) {
		this.storageState = storageState;
	}

	@Override
	public String toString() {
		return "ItemView [id=" + this.id + ", name=" + this.name + ", shelfLife=" + this.shelfLife + ", storageState="
				+ this.storageState + ", purchaseDate=" + this.purchaseDate + ", expiryDate=" + this.expiryDate + "]";
	}

}
