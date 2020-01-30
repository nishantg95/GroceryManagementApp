package selenium;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InventoryPageTests extends BaseTest {

	private static final String INVENTORY_URI = "http://localhost:8081/GroceryManagementApp/inventory";

	@Test(priority = 1)
	public void openInventoryPage() {
		driver.get(INVENTORY_URI);
		wait = new WebDriverWait(driver, 10);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
//		Assert.fail("");
		waitForAngularV1ToFinish();

		// assert something
	}

	@Test(priority = 2, dependsOnMethods = { "openInventoryPage" })
	public void addNewItem() {
		waitForAngularV1ToFinish();
		wait = new WebDriverWait(driver, 10);
		// Need expected condition to wait for click on input fields
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("name")));
//		WebElement element = driver.findElement(By.id("name"));
		element.sendKeys("Du");
		element = driver.findElement(By.className("name-typeahead"));
		element = element.findElement(By.tagName("ul"));
		multipleElements = element.findElements(By.tagName("li"));
		if (multipleElements.size() > 0)
			multipleElements.get(0).click();
		else {
			// assert failure
			// OR really add new item
		}
		Select storageSelect = new Select(driver.findElement(By.name("storage_state")));
		storageSelect.selectByVisibleText("Pantry");
		// assert failure
		element = driver.findElement(By.id("purchase_date"));
		element.sendKeys("11-11-2011");
		element = driver.findElement(By.id("addChangeButton"));
		element.click();
		// assert success
	}

	@Test(priority = 3, dependsOnMethods = { "openInventoryPage" })
	public void updateFirstItem() {
		waitForAngularV1ToFinish();
		wait = new WebDriverWait(driver, 10);
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
	@Test(priority = 4, dependsOnMethods = { "openInventoryPage" })
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

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe
	 */
	@BeforeTest
	public void beforeTest() {
		super.beforeTest();
		// Fancy re-ordering, push to top right and cover both right quadrants
		driver.manage().window().maximize();
		Dimension windowSize = driver.manage().window().getSize();
		int desiredHeight = windowSize.height;
		int desiredWidth = windowSize.width / 2; // Half the screen width
		Dimension desiredSize = new Dimension(desiredWidth, desiredHeight);
		driver.manage().window().setSize(desiredSize);
		driver.manage().window().setPosition(new Point(desiredWidth, 0));

	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
