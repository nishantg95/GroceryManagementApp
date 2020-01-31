package com.nishant.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.nishant.interfaces.ItemInterface;
import com.nishant.views.ItemView;

@Entity
@Table(name = "ITEM")
public class ItemEntity implements ItemInterface {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "LONGEVITY")
	private String longevity;

	@Column(name = "STORAGE_STATE", nullable = false)
	private String storageState;

	@Column(name = "PURCHASE_DATE", nullable = false)
	private Date purchaseDate;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "REFRIGERATE_DATE")
	private String refrigerateDateString;

	@Column(name = "PANTRY_DATE")
	private String pantryDateString;

	@Column(name = "FREEZER_DATE")
	private String freezerDateString;

	/**
	 *
	 */
	public ItemEntity() {
		// Default Constructor
	}

	public ItemEntity(ItemView itemView) {
		BeanUtils.copyProperties(itemView, this, ItemInterface.class);
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
		return "ItemEntity [id=" + this.id + ", name=" + this.name + ", longevity=" + this.longevity + ", storageState="
				+ this.storageState + ", purchaseDate=" + this.purchaseDate + ", expiryDate=" + this.expiryDate
				+ ", refrigerateDateString=" + this.refrigerateDateString + ", pantryDateString="
				+ this.pantryDateString + ", freezerDateString=" + this.freezerDateString + "]";
	}

}
