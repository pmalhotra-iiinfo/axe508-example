package gov.uscis.odds.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import gov.uscis.odds.login.LoginPage;
import gov.uscis.odds.util.Util;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.karsun.kic.tan.duke.ExecutionContext;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;
import com.paulhammant.ngwebdriver.ByAngular;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class UserPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private ExecutionContext executionContext;

	private By logoutLink = By.cssSelector("[class='logout']");
	private By fromDateInput = By.cssSelector("[name='fromDate']");
	private By toDateInput = By.cssSelector("[name='toDate']");
	private By addButton = By.xpath("//button[contains(text(),'Add')]");
	
	public UserPage(WebDriver driver) {
		super(driver);
	}
	
	public UserPage(ExecutionContext executionContext) {
		super(executionContext.getDriver());
		this.executionContext = executionContext;
	}

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(driver, logoutLink, TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.get();
		
		loginPage.login(executionContext.getCurrentScenarioObj().get("signIn").getAsJsonObject());
		Assert.assertTrue("Could not load User page", isLoaded());
	}
	
	public String getTodaysCalories() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		return ActionByLocator.getText(driver, By.cssSelector("[class*='cal-limit'] > span"), TIME_OUT_SECONDS).trim();
	}

	public void enterMeals(JsonArray meals) {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		Iterator<JsonElement> iterator = meals.iterator();
		while (iterator.hasNext()) {
			JsonObject meal = iterator.next().getAsJsonObject();
			ActionByLocator.click(driver, addButton, TIME_OUT_SECONDS);
			
			ActionByLocator.sendKeys(driver, By.xpath("//input[@ng-model='meal.datetime']"), "2017/08/07 12:00", TIME_OUT_SECONDS);
			
			String mealDescription = meal.get("description").getAsString();
			ActionByLocator.sendKeys(driver, By.xpath("//input[@ng-model='meal.description']"), mealDescription, TIME_OUT_SECONDS);
			
			String totalCalories = meal.get("calories").getAsString();
			ActionByLocator.sendKeys(driver, By.xpath("//input[@ng-model='meal.calories']"), totalCalories, TIME_OUT_SECONDS);
		}
		ActionByLocator.click(driver, By.xpath("//button[contains(text(),'Save')]"), TIME_OUT_SECONDS);
	}

	public boolean isMessageDisplayed(String message) {
		return ActionByLocator.isDisplayed(executionContext.getDriver(), By.xpath("//div[contains(text(), '"+message+"')]"), TIME_OUT_SECONDS);
	}

	public void searchFor(JsonObject searchObject) {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		String startDate = searchObject.get("startDate").getAsString().isEmpty() ? today.format(dtf) : searchObject.get("startDate").getAsString();
		String endDate = searchObject.get("endDate").getAsString().isEmpty() ? today.format(dtf) : searchObject.get("endDate").getAsString();
		
		populateStartDate(startDate);
		
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		
		populateEndDate(endDate);
		
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		
		ActionByLocator.click(driver, By.cssSelector("[class*='search-button']"), TIME_OUT_SECONDS);
	}

	private void populateStartDate(String dateString) {
		ActionByLocator.clear(driver, fromDateInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, fromDateInput, dateString, TIME_OUT_SECONDS);
		ActionByLocator.click(driver, fromDateInput, TIME_OUT_SECONDS);
	}
	
	private void populateEndDate(String dateString) {
		ActionByLocator.clear(driver, toDateInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, toDateInput, dateString, TIME_OUT_SECONDS);
		ActionByLocator.click(driver, toDateInput, TIME_OUT_SECONDS);
	}

	public int getSearchResultCount(int expectedCount) {
		NgWebDriver ng = new NgWebDriver((JavascriptExecutor) driver);
		ng.waitForAngularRequestsToFinish();
		
		List<WebElement> elements = driver.findElements(ByAngular.repeater("meal in vm.meals | excludeDeleted | limitTo : 10"));
		if (elements.size() != expectedCount) {
			Util.waitFor(5);
			elements = driver.findElements(ByAngular.repeater("meal in vm.meals | excludeDeleted | limitTo : 10"));
		}
		return elements.size();
	}

	public void clickAddButton() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		ActionByLocator.click(driver, addButton, TIME_OUT_SECONDS);
	}
	
	public String getDateTimeForEntry() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		WebElement element = driver.findElement(By.xpath("//tr[@ng-repeat='meal in vm.meals | excludeDeleted | limitTo : 10'][1]/td[2]//input[1]"));
		return element.getAttribute("value");
	}
}
