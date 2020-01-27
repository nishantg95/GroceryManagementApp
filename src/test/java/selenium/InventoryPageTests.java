package selenium;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

	@Test(priority = 2)
	public void addNewItem() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
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
	public void updateItem() {

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
		driver.close();
	}

}
