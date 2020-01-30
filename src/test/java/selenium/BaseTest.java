/**
 * 
 */
package selenium;

import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @author nishant.b.grover
 *
 */
public class BaseTest {

	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WebElement element;
	protected List<WebElement> multipleElements;

	private static final String CHROMEDRIVER_LOCATION = "C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe";

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

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe
	 */
	@BeforeTest
	public void beforeTest() {
		// Chrome driver version should match chrome browser version
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
		driver = new ChromeDriver();

	}

	/**
	 * This test adds explicit wait to allow angularjs to finish all compile and
	 * digest cycles, for the given driver. It uses $browser from AngularJS private
	 * API to call notifyWhenNoOutstandingRequests()
	 */
	public void waitForAngularV1ToFinish() {
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
