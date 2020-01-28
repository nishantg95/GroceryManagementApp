package selenium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminPanelPageTests {
	private WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(HomePageTests.class);
	private static final String ADMIN_URI = "http://localhost:8081/GroceryManagementApp/repo/viewRepoItems";
	private static final String CHROMEDRIVER_LOCATION = "C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe";

	/**
	 * This test ensures we can perform a GET request to fetch all Repo items. This
	 * can be proven by the fact that div with id "syncFormResult" is absent from
	 * the DOM, as the div only appears on a POST request
	 * 
	 * @author nishant.b.grover
	 */
	@Test(priority = 1)
	public void GetRepoItems() {
		driver.get(ADMIN_URI);
		assertEquals(driver.findElements(By.id("syncFormResult")).size(), 0);
//		syncFormResult should not be in DOM, since ng-if should yield false in jsp
	}

	/**
	 * This test ensures that clicking add button redirects to add user form
	 * 
	 * @author nishant.b.grover
	 */
	@Test(priority = 2)
	public void RepoItemForm() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("addRepoItem")));
		element.click();
		assertEquals(driver.getCurrentUrl(), "http://localhost:8081/GroceryManagementApp/repo/addRepoItemForm");
	}

	/**
	 * This test adds a Dummy Item to Repo table, using the add form that we were
	 * redirected to in the <strong>RepoItemForm()</strong> function
	 * 
	 * @author nishant.b.grover
	 */
	@Test(priority = 3)
	public void AddRepoItem() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement element = driver.findElement(By.id("item_name_Field"));
		element.sendKeys("Dummy Item");
		element = driver.findElement(By.id("repo_refrigerate_date"));
		element.sendKeys("5 days");
		element = driver.findElement(By.id("repo_pantry_date"));
		element.sendKeys("2 weeks");
		element = driver.findElement(By.id("repo_freezer_date"));
		element.sendKeys("1 month");
		element = driver.findElement(By.id("repo_item_add"));
		element.click();
		wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
		assertTrue(driver.findElement(By.id("syncFormResult")).isDisplayed());
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
		driver.quit();
	}

}
