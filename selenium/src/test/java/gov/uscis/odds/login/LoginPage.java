package gov.uscis.odds.login;

import gov.uscis.odds.util.LoadProperties;
import gov.uscis.odds.util.Util;

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
	
	private By usernameInput = By.cssSelector("[name='username']");
	private By passwordInput = By.cssSelector("[name='password']");
	private By loginButton = By.id("login");

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isLoaded() {
		return ActionByLocator.isDisplayed(driver, By.cssSelector("[name='username']"), TIME_OUT_SECONDS);
	}

	@Override
	protected void load() {
		waitForAngular();
		
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
		waitForAngular();
		return ActionByLocator.getText(driver, By.cssSelector("[ng-repeat*='errorMessages']"), TIME_OUT_SECONDS).trim();
	}

	private void loginWithRetry(String username, String password) {
		sendLoginKeys(username, password);
		
		if (ActionByLocator.getElement(driver, usernameInput, TIME_OUT_SECONDS).getAttribute("value").isEmpty()) {
			Util.waitFor(5);
			sendLoginKeys(username, password);
		}
		
		ActionByLocator.click(driver, loginButton, TIME_OUT_SECONDS);
	}

	private void sendLoginKeys(String username, String password) {
		waitForAngular();
		ActionByLocator.clear(driver, usernameInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, usernameInput, username, TIME_OUT_SECONDS);
		
		waitForAngular();
		ActionByLocator.clear(driver, passwordInput, TIME_OUT_SECONDS);
		ActionByLocator.sendKeys(driver, passwordInput, password, TIME_OUT_SECONDS);
	}

	public String getWelcomeMessage() {
		waitForAngular();
		return ActionByLocator.getText(driver, By.className("welcome-message"), TIME_OUT_SECONDS).trim();
	}
	
	private void waitForAngular() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
	}

	public boolean isImageDisplayed() {
		new NgWebDriver((JavascriptExecutor) driver).waitForAngularRequestsToFinish();
		String attr = ActionByLocator.getElement(driver, By.xpath("//body"), TIME_OUT_SECONDS).getCssValue("background");
		return attr.contains("background.svg");
	}
}
