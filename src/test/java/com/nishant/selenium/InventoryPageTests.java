package com.nishant.selenium;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryPageTests extends BaseTest {

	private static final String INVENTORY_URI = "http://localhost:8081/GroceryManagementApp/inventory";

	@Test(priority = 2, dependsOnMethods = { "openInventoryPage" })
	public void addItemWithAutocomplete() {
		this.waitForAngularV1ToFinish();
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("name")))
				.sendKeys(this.getDummyRepoItem().getrName());
		this.multipleElements = this.driver.findElements(By.cssSelector("div.name-typeahead > ul > li"));
		// Assert that item exists in repo_items
		Assert.assertTrue(this.multipleElements.size() == 1);
		this.multipleElements.get(0).click();
		Select storageSelect = new Select(this.driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Pantry");
		// Assert that longevity updated as per value of storageSelect= "Pantry"
		Assert.assertTrue(this.wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("longevity"),
				this.getDummyRepoItem().getrPantryDate())));
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase_date"))).sendKeys("11-11-2020");
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("addChangeButton"))).click();
		this.waitForAngularV1ToFinish();
		String validatorString = String.join(" ", dummyRepoItem.getrName(), "Pantry", dummyRepoItem.getrPantryDate(),
				"11-11-2020");
		this.element = this.driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(this.element.getText(), validatorString);
	}

	@Test(priority = 3, dependsOnMethods = { "openInventoryPage" })
	public void addItemWithoutAutocomplete() {
		this.waitForAngularV1ToFinish();
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Mock object!@#$");
		Select storageSelect = new Select(this.driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Freezer");
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase_date"))).sendKeys("01-11-2020");
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("addChangeButton"))).click();
		this.waitForAngularV1ToFinish();
		String validatorString = String.join(" ", "Mock object!@#$", "Freezer", "01-11-2020");
		this.element = this.driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(this.element.getText(), validatorString);
	}

	/**
	*
	*/
	@Test(priority = 5, dependsOnMethods = { "openInventoryPage" })
	private void deleteFirstItem() {
		this.waitForAngularV1ToFinish();
		this.element = this.driver.findElement(By.id("items"));
		this.multipleElements = this.element.findElements(By.cssSelector("tbody>tr"));
		System.out.println(this.multipleElements.size());
		Integer sizeBeforeDelete = this.multipleElements.size();
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("delete_button_0"))).click();
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm_delete_button"))).click();
		this.waitForAngularV1ToFinish();
		this.element = this.driver.findElement(By.id("items"));
		this.multipleElements = this.element.findElements(By.cssSelector("tbody>tr"));
		Integer sizePostDeleteInteger = this.multipleElements.size();
		System.out.println(this.multipleElements.size());
		Assert.assertTrue(sizeBeforeDelete == (sizePostDeleteInteger + 1));
	}

	@Test(priority = 1)
	public void openInventoryPage() {
		this.driver.get(INVENTORY_URI);
		this.wait = new WebDriverWait(this.driver, 10);
		this.wait.until(ExpectedConditions.urlToBe(INVENTORY_URI));
		Assert.assertTrue(
				this.wait.until(ExpectedConditions.textToBe(By.cssSelector("h3#page_title"), "Nishant's Inventory")));
	}

	@Test(priority = 4, dependsOnMethods = { "openInventoryPage", "addItemWithoutAutocomplete" })
	public void updateFirstItem() {
		this.waitForAngularV1ToFinish();
		this.wait.until(ExpectedConditions.elementToBeClickable(By.id("edit_button_0"))).click(); // click edit button
		this.element = this.driver.findElement(By.id("name"));
		this.element.clear();
		this.element.sendKeys("Brownies"); // update name

		Select storageSelect = new Select(this.driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Freezer");// update storage_state

		this.element = this.driver.findElement(By.id("purchase_date"));
		String purchaseString = this.element.getAttribute("value");
		this.element = this.driver.findElement(By.id("expiry_date"));
		this.element.clear();
		String today = new SimpleDateFormat("MM-dd-YYYY").format(new Date());
		this.element.sendKeys(today);// update expiry to today

		Period period = Period.between(LocalDate.parse(purchaseString, DateTimeFormatter.ofPattern("MM-dd-yyyy")),
				LocalDate.parse(today, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		this.element = this.driver.findElement(By.id("longevity"));
		this.element.clear();
		this.element.sendKeys(String.join(" ", Integer.toString(period.getDays()), "days"));// update Longevity

		this.element = this.driver.findElement(By.id("addChangeButton"));
		this.element.click();

		this.waitForAngularV1ToFinish();
		String validatorString = String.join(" ", "Brownies", "Freezer", period.getDays() + " days", "01-11-2020",
				today);
		this.element = this.driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(this.element.getText(), validatorString);
	}

}
