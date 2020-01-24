package selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTests {

	private WebDriver driver;
	private static final String HOME_URI = "http://localhost:8081/GroceryManagementApp";
	private static final String CHROMEDRIVER_LOCATION = "C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe";

	/**
	 * This test ensures the continuity of the web application by:<br>
	 * 1. Loading home page<br>
	 * 2. Click on "Inventory" button to <strong>navigates to Inventory
	 * page</strong>.<br>
	 * 3. Click on "back" button from Inventory page, which <strong>redirects back
	 * to home page.</strong>
	 * 
	 * @author nishant.b.grover
	 */
	@Test
	public void navigateToInventory() {
		driver.get(HOME_URI);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("asyncPageLink")));
		element.click();
		assertEquals(driver.getCurrentUrl(), HOME_URI + "/inventory");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromAsync")));
		element.click();
		assertEquals(driver.getCurrentUrl(), HOME_URI + "/welcome");
	}

	/**
	 * This test ensures the continuity of the web application by:<br>
	 * 1. Loading home page<br>
	 * 2. Click on "Admin Panel" button to <strong>navigates to admin
	 * page</strong>.<br>
	 * 3. Click on "back" button from Admin Panel page, which <strong>redirects back
	 * to home page.</strong>
	 * 
	 * @author nishant.b.grover
	 */
	@Test
	public void navigateToAdmin() {
		driver.get(HOME_URI);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("syncPageLink")));
		element.click();
		assertEquals(driver.getCurrentUrl(), HOME_URI + "/repo/viewRepoItems");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromAsync")));
		element.click();
		assertEquals(driver.getCurrentUrl(), HOME_URI + "/welcome");
	}

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe using <strong>CHROMEDRIVER_LOCATION</strong>
	 * 
	 * @author nishant.b.grover
	 */
	@BeforeTest
	public void beforeTest() {
		// Chrome driver for chrome version 79
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
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
