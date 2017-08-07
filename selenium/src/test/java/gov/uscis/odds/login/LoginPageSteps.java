package gov.uscis.odds.login;

import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class LoginPageSteps extends Steps {
	private LoginPage loginPage;
	
	private void init() {
		if (loginPage == null) {
			loginPage = new LoginPage(executionContext.getDriver());
		}
		loginPage.get();
	}
	
	@Given("^I am on the login page$")
	public void i_am_on_the_login_page() {
	   init();
	}

	@When("^I use valid username and password$")
	public void user_login() {
	    loginPage.login(executionContext.getCurrentScenarioObj().get("signIn").getAsJsonObject());
	}

	@When("^I use invalid username and password$")
	public void i_use_invalid_username_and_password() {
		user_login();
	}
	
	@Then("^I receive an \"([^\"]*)\" message$")
	public void i_receive_an_message(String errorMessage) {
		Assert.assertEquals("Error message does not match", errorMessage, loginPage.getErrorMessage());
	}
	
	@Then("^the application purpose is clearly stated$")
	public void the_application_purpose_is_clearly_stated() {
		init();
		String welcomeMessage = executionContext.getCurrentScenarioObj().get("welcomeMessage").getAsString();
		Assert.assertTrue("Welcome message is not as expected", loginPage.getWelcomeMessage().contains(welcomeMessage));
	}
}
