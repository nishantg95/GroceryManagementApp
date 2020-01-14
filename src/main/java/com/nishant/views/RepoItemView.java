/**
 *
 */
package com.nishant.views;

import com.nishant.interfaces.RepoItemsInterface;

/**
 * @author nishant.b.grover
 *
 */

public class RepoItemView implements RepoItemsInterface {

	private Integer rId;

	private String rName;

	private String rFridgeDate;

	private String rPantryDate;

	private String rFreezeDate;

	@Override
	public String getrFreezeDate() {
		return this.rFreezeDate;
	}

	@Override
	public String getrFridgeDate() {
		return this.rFridgeDate;
	}

	@Override
	public Integer getrId() {
		return this.rId;
	}

	@Override
	public String getrName() {
		return this.rName;
	}

	@Override
	public String getrPantryDate() {
		return this.rPantryDate;
	}

	@Override
	public void setrFreezeDate(String rFreezeDate) {
		this.rFreezeDate = rFreezeDate;

	}

	@Override
	public void setrFridgeDate(String rFridgeDate) {
		this.rFridgeDate = rFridgeDate;
	}

	@Override
	public void setrId(Integer rId) {
		this.rId = rId;
	}

	@Override
	public void setrName(String rName) {
		this.rName = rName;
	}

	@Override
	public void setrPantryDate(String rPantryDate) {
		this.rPantryDate = rPantryDate;
	}

	@Override
	public String toString() {
		return "RepoItemView [rId=" + this.rId + ", rName=" + this.rName + ", rFridgeDate=" + this.rFridgeDate
				+ ", rPantryDate=" + this.rPantryDate + ", rFreezeDate=" + this.rFreezeDate + "]";
	}
}
