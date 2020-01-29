package selenium;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminPanelPageTests {
	private WebDriver driver;
	private WebDriverWait wait;
	private WebElement element;
	private List<WebElement> multipleElements;
	private static final String ADMIN_URI = "http://localhost:8081/GroceryManagementApp/repo/viewRepoItems";
	private static final String CHROMEDRIVER_LOCATION = "C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe";

	/**
	 * This test ensures we can perform a GET request to fetch all Repo items. This
	 * can be proven by the fact that div with id "syncFormResult" is absent from
	 * the DOM, as the div only appears on a POST request
	 */
	@Test(priority = 1)
	public void GetRepoItems() {
		driver.get(ADMIN_URI);
		wait = new WebDriverWait(driver, 10);
		checkIfOffline();
		multipleElements = driver.findElements(By.id("repo_items"));
		// Should be visible for a HTTP GET request
		Assert.assertEquals(multipleElements.size(), 1, "Did not find repo_items table in DOM");
		multipleElements = driver.findElements(By.id("syncFormResult"));
		// Should not be visible for HTTP GET request
		Assert.assertEquals(multipleElements.size(), 0, "div with id syncFormResult was visible on a GET request");
		Assert.assertEquals(driver.getCurrentUrl(), ADMIN_URI);
	}

	/**
	 * This test ensures that clicking add button redirects to add user form
	 */
	@Test(priority = 2, dependsOnMethods = { "GetRepoItems" })
	public void RepoItemForm() {
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("addRepoItem")));
		element.click();
		checkIfOffline();
		wait.until(ExpectedConditions.urlToBe("http://localhost:8081/GroceryManagementApp/repo/addRepoItemForm"));
		multipleElements = driver.findElements(By.id("repo_item_form"));
		Assert.assertEquals(multipleElements.size(), 1, "Missing <form> to add new repo item");
	}

	/**
	 * This test adds a Dummy Item to Repo table, using the add form that we were
	 * redirected to in the <strong>RepoItemForm()</strong> function
	 */
	@Test(priority = 3, dependsOnMethods = { "GetRepoItems" })
	public void AddRepoItem() {
		element = driver.findElement(By.id("item_name_Field"));
		String itemName = "Dummy Item_" + Calendar.getInstance().getTimeInMillis();
		element.sendKeys(itemName);
		element = driver.findElement(By.id("repo_refrigerate_date"));
		element.sendKeys("5 days");
		element = driver.findElement(By.id("repo_pantry_date"));
		element.sendKeys("2 weeks");
		element = driver.findElement(By.id("repo_freezer_date"));
		element.sendKeys("1 month");
		element = driver.findElement(By.id("repo_item_add"));
		element.click();
		wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
		Assert.assertTrue(driver.findElement(By.id("syncFormResult")).isDisplayed());
	}

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe
	 */
	@BeforeTest
	public void beforeTest() {
		// Chrome driver version should match chrome browser version
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
		driver = new ChromeDriver();
		// Fancy re-ordering, push to bottom left quadrant
		driver.manage().window().maximize();
		Dimension windowSize = driver.manage().window().getSize();
		int desiredHeight = windowSize.height / 2;
		int desiredWidth = windowSize.width / 2; // Half the screen width
		Dimension desiredSize = new Dimension(desiredWidth, desiredHeight);
		driver.manage().window().setSize(desiredSize);
		driver.manage().window().setPosition(new Point(0, desiredHeight));
	}

	/**
	 * Closes the Chrome driver that was created and initialized in
	 * <strong>beforeTest() </strong>
	 */
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

	public void checkIfOffline() {
		multipleElements = driver.findElements(By.className("error-code"));
		if (multipleElements.size() != 0) {
			Assert.fail("Was unable to establish network connection. Front end server is down perhaps?");
		}

	}

}
