package selenium;

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

	@Test(priority = 1)
	public void openInventoryPage() {
		driver.get(INVENTORY_URI);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlToBe(INVENTORY_URI));
		Assert.assertTrue(
				wait.until(ExpectedConditions.textToBe(By.cssSelector("h3#page_title"), "Nishant's Inventory")));
	}

	@Test(priority = 2, dependsOnMethods = { "openInventoryPage" })
	public void addItemWithAutocomplete() {
		waitForAngularV1ToFinish();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys(getDummyRepoItem().getrName());
		multipleElements = driver.findElements(By.cssSelector("div.name-typeahead > ul > li"));
		// Assert that item exists in repo_items
		Assert.assertTrue(multipleElements.size() == 1);
		multipleElements.get(0).click();
		Select storageSelect = new Select(driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Pantry");
		// Assert that longevity updated as per value of storageSelect= "Pantry"
		Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementValue(By.id("longevity"),
				getDummyRepoItem().getrPantryDate())));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase_date"))).sendKeys("11-11-2020");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addChangeButton"))).click();
		waitForAngularV1ToFinish();
		String validatorString = String.join(" ", dummyRepoItem.getrName(), "Pantry", dummyRepoItem.getrPantryDate(),
				"11-11-2020");
		element = driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(element.getText(), validatorString);
	}

	@Test(priority = 3, dependsOnMethods = { "openInventoryPage" })
	public void addItemWithoutAutocomplete() {
		waitForAngularV1ToFinish();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).sendKeys("Mock object!@#$");
		Select storageSelect = new Select(driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Freezer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase_date"))).sendKeys("01-11-2020");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addChangeButton"))).click();
		waitForAngularV1ToFinish();
		String validatorString = String.join(" ", "Mock object!@#$", "Freezer", "01-11-2020");
		element = driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(element.getText(), validatorString);
	}

	@Test(priority = 4, dependsOnMethods = { "openInventoryPage", "addItemWithoutAutocomplete" })
	public void updateFirstItem() {
		waitForAngularV1ToFinish();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("edit_button_0"))).click(); // click edit button
		element = driver.findElement(By.id("name"));
		element.clear();
		element.sendKeys("Brownies"); // update name

		Select storageSelect = new Select(driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Freezer");// update storage_state

		element = driver.findElement(By.id("purchase_date"));
		String purchaseString = element.getAttribute("value");
		element = driver.findElement(By.id("expiry_date"));
		element.clear();
		String today = new SimpleDateFormat("MM-dd-YYYY").format(new Date());
		element.sendKeys(today);// update expiry to today

		Period period = Period.between(LocalDate.parse(purchaseString, DateTimeFormatter.ofPattern("MM-dd-yyyy")),
				LocalDate.parse(today, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		element = driver.findElement(By.id("longevity"));
		element.clear();
		element.sendKeys(String.join(" ", Integer.toString(period.getDays()), "days"));// update Longevity

		element = driver.findElement(By.id("addChangeButton"));
		element.click();

		waitForAngularV1ToFinish();
		String validatorString = String.join(" ", "Brownies", "Freezer", period.getDays() + " days", "01-11-2020",
				today);
		element = driver.findElement(By.id("item_0"));
		// Assert that item was added to the top of the list
		Assert.assertEquals(element.getText(), validatorString);
	}

	/**
	* 
	*/
	@Test(priority = 5, dependsOnMethods = { "openInventoryPage" })
	private void deleteFirstItem() {
		waitForAngularV1ToFinish();
		element = driver.findElement(By.id("items"));
		multipleElements = element.findElements(By.cssSelector("tbody>tr"));
		System.out.println(multipleElements.size());
		Integer sizeBeforeDelete = multipleElements.size();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("delete_button_0"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("confirm_delete_button"))).click();
		waitForAngularV1ToFinish();
		element = driver.findElement(By.id("items"));
		multipleElements = element.findElements(By.cssSelector("tbody>tr"));
		Integer sizePostDeleteInteger = multipleElements.size();
		System.out.println(multipleElements.size());
		Assert.assertTrue(sizeBeforeDelete == (sizePostDeleteInteger + 1));
	}

}
