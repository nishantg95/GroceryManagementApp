package selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HomePageTests extends BaseTest {

	private static final String HOME_URI = "http://localhost:8081/GroceryManagementApp";

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
		driver.get(HOME_URI);
		wait = new WebDriverWait(driver, 10);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("asyncPageLink")));
		element.click();
		Assert.assertEquals(driver.getCurrentUrl(), HOME_URI + "/inventory");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromSync")));
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
	 */
	@Test
	public void navigateToAdmin() {
		driver.get(HOME_URI);
		wait = new WebDriverWait(driver, 10);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("syncPageLink")));
		element.click();
		Assert.assertEquals(driver.getCurrentUrl(), HOME_URI + "/repo/viewRepoItems");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("backFromAsync")));
		element.click();
		Assert.assertEquals(driver.getCurrentUrl(), HOME_URI + "/welcome");
	}

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe using <strong>CHROMEDRIVER_LOCATION</strong>
	 */
	@BeforeTest
	public void beforeTest() {
		super.beforeTest();
		// Fancy re-ordering, push to top left quadrant
		driver.manage().window().maximize();
		Dimension windowSize = driver.manage().window().getSize();
		int desiredHeight = windowSize.height / 2; // Half the screen height
		int desiredWidth = windowSize.width / 2; // Half the screen width
		Dimension desiredSize = new Dimension(desiredWidth, desiredHeight);
		driver.manage().window().setSize(desiredSize);
		driver.manage().window().setPosition(new Point(0, 0));

	}

	@AfterTest
	public void afterTest() {
		super.afterTest();
	}

}
