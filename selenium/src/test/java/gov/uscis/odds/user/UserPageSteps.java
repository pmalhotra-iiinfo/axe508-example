package gov.uscis.odds.user;


import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@Component
public class UserPageSteps extends Steps {
	private UserPage userPage;
	
	private void init() {
		if (userPage == null) {
			userPage = new UserPage(executionContext);
		}
		userPage.get();
	}
	
	@Then("^my calories for today are displayed$")
	public void my_calories_for_today_are_displayed() {
		init();
		String todaysCalories = executionContext.getCurrentScenarioObj().get("todaysCalories").getAsString();
		Assert.assertEquals("Today's calories do not match", todaysCalories, userPage.getTodaysCalories());
	}
	
	@Given("^I am on the user page$")
	public void i_am_on_the_user_page() {
		init();
	}
	
	@When("^I add a meal entry$")
	public void i_add_a_meal_entry() {
		init();
		userPage.enterMeals(executionContext.getCurrentScenarioObj().get("meals").getAsJsonArray());
	}
	
	@Then("^I receive a confirmation message$")
	public void i_receive_a_confirmation_message() {
		init();
		String message = executionContext.getCurrentScenarioObj().get("message").getAsString();
		Assert.assertTrue("Message not as expected", userPage.isMessageDisplayed(message));
	}
}
