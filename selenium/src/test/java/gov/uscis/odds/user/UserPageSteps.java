package gov.uscis.odds.user;


import gov.uscis.odds.util.Util;

import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.karsun.kic.tan.duke.Steps;

import cucumber.api.PendingException;
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
		
		if (userPage.isMessageDisplayed("No results found.")) {
			Util.waitFor(10);
		}
		
		userPage.enterMeals(executionContext.getCurrentScenarioObj().get("meals").getAsJsonArray());
	}
	
	@Then("^I receive a confirmation message$")
	public void i_receive_a_confirmation_message() {
		init();
		String message = executionContext.getCurrentScenarioObj().get("message").getAsString();
		Assert.assertTrue("Message not as expected", userPage.isMessageDisplayed(message));
	}
	
	@When("^I add meal entries$")
	public void i_add_meal_entries() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I delete a meal entry$")
	public void i_delete_a_meal_entry() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the number of calories for the day is updated$")
	public void the_number_of_calories_for_the_day_is_updated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I add a meal entry without its type$")
	public void i_add_a_meal_entry_without_its_type() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I receive an error message$")
	public void i_receive_an_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I add a meal entry without specifying calories$")
	public void i_add_a_meal_entry_without_specifying_calories() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I create a new user$")
	public void i_create_a_new_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Given("^there is no data from last three days$")
	public void there_is_no_data_from_last_three_days() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I do not specify a date-time period to search$")
	public void i_do_not_specify_a_date_time_period_to_search() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^I receive a message \"([^\"]*)\"$")
	public void i_receive_a_message(String arg1) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I specify a date period to search$")
	public void i_specify_a_date_period_to_search() {
	    init();
	    userPage.searchFor(executionContext.getCurrentScenarioObj().get("search").getAsJsonObject());
	}

	@Then("^all existing records are returned$")
	public void all_existing_records_are_returned() {
	    int actualSearchCount = userPage.getSearchResultCount();
	    JsonObject searchObj = executionContext.getCurrentScenarioObj().get("search").getAsJsonObject();   
	    
	    Assert.assertEquals("Search count", searchObj.get("count").getAsInt(), actualSearchCount);
	}

	@When("^I specify a date-time period to search$")
	public void i_specify_a_date_time_period_to_search() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^I specify a time period to search$")
	public void i_specify_a_time_period_to_search() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
