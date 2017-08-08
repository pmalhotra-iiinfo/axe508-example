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
import com.paulhammant.ngwebdriver.NgWebDriver;

public class UserPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	private ExecutionContext executionContext;

	By logoutLink = By.cssSelector("[class='logout']");
	
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
			ActionByLocator.click(driver, By.xpath("//button[contains(text(),'Add')]"), TIME_OUT_SECONDS);
			
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
		
		ActionByLocator.clear(driver, By.cssSelector("[name='fromDate']"), TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, By.cssSelector("[name='fromDate']"), startDate, TIME_OUT_SECONDS);
		ActionByLocator.click(driver, By.cssSelector("[ng-model='vm.maxCaloriesPerDay']"), TIME_OUT_SECONDS);
		
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		
		ActionByLocator.clear(driver, By.cssSelector("[name='toDate']"), TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, By.cssSelector("[name='toDate']"), endDate, TIME_OUT_SECONDS);
		ActionByLocator.click(driver, By.cssSelector("[ng-model='vm.maxCaloriesPerDay']"), TIME_OUT_SECONDS);
		
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		
		ActionByLocator.click(driver, By.cssSelector("[class*='search-button']"), TIME_OUT_SECONDS);
	}

	public int getSearchResultCount() {
		Util.waitFor(5);
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		List<WebElement> elements = ActionByLocator.getElements(driver, By.cssSelector("[ng-repeat*='meal in vm.meals']"), TIME_OUT_SECONDS);
		return elements.size();
	}
}
