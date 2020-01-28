package selenium;

import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InventoryPageTests {

	private WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(HomePageTests.class);
	private static final String INVENTORY_URI = "http://localhost:8081/GroceryManagementApp/inventory";

	@Test(priority = 1)
	public void openInventoryPage() {
		driver.get(INVENTORY_URI);
		// assert something
	}

//	@Test(priority = 2)
	public void addNewItem() {
//		WebDriverWait wait = new WebDriverWait(driver, 10);
		// Need expected condition to wait for click on input fields
		WebElement element = driver.findElement(By.id("name"));
		element.sendKeys("Du");
		element = driver.findElement(By.className("name-typeahead"));
		element = element.findElement(By.tagName("ul"));
		List<WebElement> autoFillList = element.findElements(By.tagName("li"));
		if (autoFillList.size() > 0)
			autoFillList.get(0).click();
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

	@Test(priority = 3)
	public void updateFirstItem() {
//		waitForAngularV1ToFinish(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = driver.findElement(By.id("items"));
		List<WebElement> body = element.findElements(By.cssSelector("tbody>tr"));
		System.out.println("Number of Items= " + body.size());
		for (WebElement e : body) {
			System.out.println(e.getAttribute("id"));
		}
		element = body.get(0).findElement(By.id("edit_button"));
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
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe
	 * 
	 * @author nishant.b.grover
	 */
	@BeforeTest
	public void beforeTest() {
		// Chrome driver version that you download should match your chrome browser
		// version
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	/**
	 * Closes the Chrome driver that was created and initialized in
	 * <strong>beforeTest() </strong>
	 * 
	 * @author nishant.b.grover
	 */
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public void waitForAngularV1ToFinish(WebDriver driver) {
		final String query = "window.angularFinished;" + "waitForAngular = function() {"
				+ " window.angularFinished = false;" + " var el = document.querySelector('body');"
				+ " var callback = (function(){window.angularFinished=1});"
				+ " angular.element(el).injector().get('$browser')." + " notifyWhenNoOutstandingRequests(callback);};";
		try {
			((JavascriptExecutor) driver).executeScript(query);
			((JavascriptExecutor) driver).executeScript("waitForAngular()");

			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					Object noAjaxRequests = ((JavascriptExecutor) driver)
							.executeScript("return window.angularFinished;");
					return "1".equals(noAjaxRequests.toString());
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			fail("Unable to load the page correctly");
		}
	}
}
