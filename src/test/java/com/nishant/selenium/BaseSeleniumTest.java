package com.nishant.selenium;

import static org.testng.Assert.fail;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.nishant.entities.RepoItemEntity;

/**
 * @author nishant.b.grover
 *
 */
public class BaseSeleniumTest {

	protected static RepoItemEntity dummyRepoItem;
	private static final String CHROMEDRIVER_LOCATION = "C:\\Users\\nishant.b.grover\\Downloads\\chromedriver_win32\\chromedriver.exe";
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected WebElement element;

	protected List<WebElement> multipleElements;

	/**
	 * Closes the Chrome driver that was created and initialized in
	 * <strong>beforeTest() </strong>
	 */
	@AfterClass
	public void afterClass() {
		this.driver.quit();
	}

	/**
	 * Creates new Chrome Driver before executing tests. Must download and specify
	 * location of chromedriver.exe
	 */
	@BeforeClass
	public void beforeClass() {
		// Chrome driver version should match chrome browser version
		System.setProperty("webdriver.chrome.driver", CHROMEDRIVER_LOCATION);
		this.driver = new ChromeDriver();
		this.driver.manage().window().maximize();

	}

	/**
	 * @return the dummyRepoItem
	 */
	public RepoItemEntity getDummyRepoItem() {
		return dummyRepoItem;
	}

	/**
	 * @param dummyRepoItem the dummyRepoItem to set
	 */
	public void setDummyRepoItem(RepoItemEntity dummyRepoItem) {
		BaseSeleniumTest.dummyRepoItem = dummyRepoItem;
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
			((JavascriptExecutor) this.driver).executeScript(query);
			((JavascriptExecutor) this.driver).executeScript("waitForAngular()");

			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					Object noAjaxRequests = ((JavascriptExecutor) driver)
							.executeScript("return window.angularFinished;");
					return "1".equals(noAjaxRequests.toString());
				}
			};
			this.wait.until(pageLoadCondition);
		} catch (Exception e) {
			fail("Unable to load the page correctly");
		}
	}

}
