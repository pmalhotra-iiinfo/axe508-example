package gov.uscis.odds.user;

import gov.uscis.odds.login.LoginPage;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

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
}
