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
//		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
	}

	@Test(priority = 2, dependsOnMethods = { "openInventoryPage" })
	public void addNewItem() {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.id("purchase_date"))).sendKeys("11-11-2011");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addChangeButton"))).click();
		// assert add was successful
	}

//	@Test(priority = 3, dependsOnMethods = { "openInventoryPage" })
	public void updateFirstItem() {
		waitForAngularV1ToFinish();
		element = driver.findElement(By.id("items"));
		multipleElements = element.findElements(By.cssSelector("tbody>tr"));
		System.out.println("Number of Items= " + multipleElements.size());
		element = multipleElements.get(0).findElement(By.id("edit_button"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();

		element = driver.findElement(By.id("name"));
		element.clear();
		element.sendKeys("Brownies");
		Select storageSelect = new Select(driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Freezer");
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// assert failure
		element = driver.findElement(By.id("purchase_date"));
		String purchaseString = element.getAttribute("value");
		element = driver.findElement(By.id("expiry_date"));
		element.clear();
		// create the date for today
		String today = new SimpleDateFormat("MM-dd-YYYY").format(new Date());
		element.sendKeys(today);
		Period period = Period.between(LocalDate.parse(purchaseString, DateTimeFormatter.ofPattern("MM-dd-yyyy")),
				LocalDate.parse(today, DateTimeFormatter.ofPattern("MM-dd-yyyy")));
		System.out.println("Period= " + period.getDays() + " days");
		element = driver.findElement(By.id("longevity"));
		element.clear();
		element.sendKeys(String.join(" ", Integer.toString(period.getDays()), "days"));
		element = driver.findElement(By.id("addChangeButton"));
		element.click();

	}

	/**
	* 
	*/
//	@Test(priority = 4, dependsOnMethods = { "openInventoryPage" })
	private void deleteFirstItem() {
		waitForAngularV1ToFinish();
		wait = new WebDriverWait(driver, 10);
		element = driver.findElement(By.id("items"));
		multipleElements = element.findElements(By.cssSelector("tbody>tr"));
		System.out.println("Number of Items= " + multipleElements.size());
		element = multipleElements.get(0).findElement(By.id("delete_button"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		element = driver.findElement(By.id("confirm_delete_button"));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

}
