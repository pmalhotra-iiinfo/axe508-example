package gov.uscis.odds.login;

import gov.uscis.odds.util.LoadProperties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.google.gson.JsonObject;
import com.karsun.kic.tan.duke.Page;
import com.karsun.kic.tan.duke.util.ActionByLocator;
import com.paulhammant.ngwebdriver.NgWebDriver;

public class LoginPage extends Page {
	private static final int TIME_OUT_SECONDS = 5;
	
	By usernameInput = By.cssSelector("[name='username']");
	By passwordInput = By.cssSelector("[name='password']");
	By loginButton = By.cssSelector("[class*='pure-button-primary']");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(driver, By.cssSelector("[name='username']"), TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		
		if (ActionByLocator.isDisplayed(driver, By.cssSelector("[class='logout']"), TIME_OUT_SECONDS)) {
			ActionByLocator.click(driver, By.cssSelector("[class='logout']"), TIME_OUT_SECONDS);
		} else {
			driver.get(LoadProperties.getProperty("web.url"));
		}
		
		Assert.assertTrue("Could not load Login page", isLoaded());
	}

	public void login(JsonObject loginObject) {		
		String username = loginObject.get("username").getAsString();
		String password = loginObject.get("password").getAsString();
		loginWithRetry(username, password);
	}

	public String getErrorMessage() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		return ActionByLocator.getText(driver, By.cssSelector("[ng-repeat*='errorMessages']"), TIME_OUT_SECONDS).trim();
	}

	private void loginWithRetry(String username, String password) {
		sendLoginKeys(username, password);
		
		if (ActionByLocator.getText(driver, usernameInput, TIME_OUT_SECONDS).isEmpty()) {
			ActionByLocator.clear(driver, usernameInput, TIME_OUT_SECONDS);
			ActionByLocator.clear(driver, passwordInput, TIME_OUT_SECONDS);
			sendLoginKeys(username, password);
		}
		
		ActionByLocator.click(driver, loginButton, TIME_OUT_SECONDS);
	}

	private void sendLoginKeys(String username, String password) {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		ActionByLocator.sendKeys(driver, usernameInput, username, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, passwordInput, password, TIME_OUT_SECONDS);
	}

	public String getWelcomeMessage() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		return ActionByLocator.getText(driver, By.className("welcome-message"), TIME_OUT_SECONDS).trim();
	}
}
