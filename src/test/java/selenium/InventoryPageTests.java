package selenium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InventoryPageTests {

	private WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(HomePageTests.class);
	private static final String INVENTORY_URI = "http://localhost:8081/GroceryManagementApp/inventory";

	@Test(priority = 1)
	public void f() {
	}

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
