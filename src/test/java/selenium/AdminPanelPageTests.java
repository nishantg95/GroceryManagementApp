package selenium;

import java.util.Calendar;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.nishant.views.RepoItemView;

public class AdminPanelPageTests extends BaseTest {

	private static final String ADMIN_URI = "http://localhost:8081/GroceryManagementApp/repo/viewRepoItems";

	/**
	 * This test ensures we can perform a GET request to fetch all Repo items. This
	 * can be proven by the fact that div with id "syncFormResult" is absent from
	 * the DOM, as the div only appears on a POST request
	 */
	@Test(priority = 1)
	public void GetRepoItems() {
		driver.get(ADMIN_URI);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
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
		dummyRepoItem = generateDummyItem();
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("item_name_Field")));
		element.sendKeys(dummyRepoItem.getrName());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_refrigerate_date")));
		element.sendKeys(dummyRepoItem.getrFridgeDate());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_pantry_date")));
		element.sendKeys(dummyRepoItem.getrPantryDate());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_freezer_date")));
		element.sendKeys(dummyRepoItem.getrFreezeDate());
		element = wait.until(ExpectedConditions.elementToBeClickable(By.id("repo_item_add")));
		element.click();
		wait.until(ExpectedConditions.urlToBe(ADMIN_URI));
		Assert.assertTrue(driver.findElement(By.id("syncFormResult")).isDisplayed());
	}

	public RepoItemView generateDummyItem() {
		RepoItemView dummyRepoItem = new RepoItemView();
		dummyRepoItem.setrName("Dummy Item_" + Calendar.getInstance().getTimeInMillis());
		Random random = new Random();
		dummyRepoItem.setrPantryDate(random.nextInt(10) + " days");
		dummyRepoItem.setrFridgeDate(random.nextInt(6) + " weeks");
		dummyRepoItem.setrFreezeDate(random.nextInt(4) + " months");
		return dummyRepoItem;

	}

}
