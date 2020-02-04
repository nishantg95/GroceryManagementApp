package com.nishant.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

	private static final String HOME_URI = "http://localhost:8081/GroceryManagementApp";

	/**
	 * This test ensures the continuity of the web application by:<br>
	 * 1. Loading home page<br>
	 * 2. Click on "Admin Panel" button to <strong>navigates to admin
	 * page</strong>.<br>
	 * 3. Click on "back" button from Admin Panel page, which <strong>redirects back
	 * to home page.</strong>
	 */
	@Test
	public void navigateToAdmin() {
		this.driver.get(HOME_URI);
		this.wait = new WebDriverWait(this.driver, 10);
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("syncPageLink")));
		this.element.click();
		Assert.assertEquals(this.driver.getCurrentUrl(), HOME_URI + "/repo/viewRepoItems");
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromAsync")));
		this.element.click();
		Assert.assertEquals(this.driver.getCurrentUrl(), HOME_URI + "/welcome");
	}

	/**
	 * This test ensures the continuity of the web application by:<br>
	 * 1. Loading home page<br>
	 * 2. Click on "Inventory" button to <strong>navigates to Inventory
	 * page</strong>.<br>
	 * 3. Click on "back" button from Inventory page, which <strong>redirects back
	 * to home page.</strong>
	 */
	@Test
	public void navigateToInventory() {
		this.driver.get(HOME_URI);
		this.wait = new WebDriverWait(this.driver, 10);
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("asyncPageLink")));
		this.element.click();
		Assert.assertEquals(this.driver.getCurrentUrl(), HOME_URI + "/inventory");
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromSync")));
		this.element.click();
		assertEquals(this.driver.getCurrentUrl(), HOME_URI + "/welcome");
	}
}
