package com.nishant.selenium;

import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nishant.entities.RepoItemEntity;

public class AdminPanelPageTests extends BaseSeleniumTest {

	private static final String ADMIN_URI = "http://localhost:8081/GroceryManagementApp/repo/viewRepoItems";

	/**
	 * This test adds a Dummy Item to Repo table, using the add form that we were
	 * redirected to in the <strong>RepoItemForm()</strong> function
	 */
	@Test(priority = 3, dependsOnMethods = { "GetRepoItems" })
	public void AddRepoItem() {
		dummyRepoItem = this.generateDummyItem();
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("item_name_Field")));
		this.element.sendKeys(dummyRepoItem.getrName());
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_refrigerate_date")));
		this.element.sendKeys(dummyRepoItem.getrFridgeDate());
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_pantry_date")));
		this.element.sendKeys(dummyRepoItem.getrPantryDate());
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_freezer_date")));
		this.element.sendKeys(dummyRepoItem.getrFreezeDate());
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_item_add")));
		this.element.click();
		this.wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
		Assert.assertTrue(this.driver.findElement(By.id("syncFormResult")).isDisplayed());
	}

	public RepoItemEntity generateDummyItem() {
		RepoItemEntity dummyRepoItem = new RepoItemEntity();
		dummyRepoItem.setrName("Dummy Item_" + Calendar.getInstance().getTimeInMillis());
		Random random = new Random();
		dummyRepoItem.setrPantryDate(random.nextInt(10) + " days");
		dummyRepoItem.setrFridgeDate(random.nextInt(6) + " weeks");
		dummyRepoItem.setrFreezeDate(random.nextInt(4) + " months");
		return dummyRepoItem;

	}

	/**
	 * This test ensures we can perform a GET request to fetch all Repo items. This
	 * can be proven by the fact that div with id "syncFormResult" is absent from
	 * the DOM, as the div only appears on a POST request
	 */
	@Test(priority = 1)
	public void GetRepoItems() {
		this.driver.get(ADMIN_URI);
		this.wait = new WebDriverWait(this.driver, 10);
		this.wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
		this.multipleElements = this.driver.findElements(By.id("repo_items"));
		// Should be visible for a HTTP GET request
		Assert.assertEquals(this.multipleElements.size(), 1, "Did not find repo_items table in DOM");
		this.multipleElements = this.driver.findElements(By.id("syncFormResult"));
		// Should not be visible for HTTP GET request
		Assert.assertEquals(this.multipleElements.size(), 0, "div with id syncFormResult was visible on a GET request");
		Assert.assertEquals(this.driver.getCurrentUrl(), ADMIN_URI);
	}

	/**
	 * This test ensures that clicking add button redirects to add user form
	 */
	@Test(priority = 2, dependsOnMethods = { "GetRepoItems" })
	public void RepoItemForm() {
		this.element = this.wait.until(ExpectedConditions.elementToBeClickable(By.id("addRepoItem")));
		this.element.click();
		this.wait.until(ExpectedConditions.urlToBe("http://localhost:8081/GroceryManagementApp/repo/addRepoItemForm"));
		this.multipleElements = this.driver.findElements(By.id("repo_item_form"));
		Assert.assertEquals(this.multipleElements.size(), 1, "Missing <form> to add new repo item");
	}

}
