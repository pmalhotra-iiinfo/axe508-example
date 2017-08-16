package gov.uscis.odds.user;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
		userPage.reset();
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
	    JsonObject searchObj = executionContext.getCurrentScenarioObj().get("search").getAsJsonObject(); 
	    
	    int expectedCount = searchObj.get("count").getAsInt();
	    Assert.assertEquals("Record count is incorrect", expectedCount, userPage.getSearchResultCount(expectedCount));
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
	
	@Then("^consecutive meal entries show current date-time$")
	public void consecutive_meal_entries_show_current_date_time() {
		init();
		if (userPage.isMessageDisplayed("No results found.")) {
			Util.waitFor(10);
		}
		userPage.clickAddButton();
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
		
		String dateTime = userPage.getDateTimeForEntry(0); // first row on table
		Assert.assertTrue("Date in the first row is not today", dateTime.contains(today.format(dtf)));
	}
	
	@When("^I search for a specific meal description with no dates$")
	public void i_search_for_a_specific_meal_description_with_no_dates() {
		init();
		JsonObject searchObj = executionContext.getCurrentScenarioObj().get("search").getAsJsonObject(); 
		userPage.searchFor(searchObj);
	}
	
	@When("^I search for a specific meal description with dates$")
	public void i_search_for_a_specific_meal_description_with_dates() {
		// Use the same method as "with" dates but making sure
		// data is updated
		i_search_for_a_specific_meal_description_with_no_dates();
	}
	
	@When("^I do not specify a date period to search$")
	public void i_do_not_specify_a_date_period_to_search() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the last three days of records are displayed$")
	public void the_last_three_days_of_records_are_displayed() {
		all_existing_records_are_returned();
	}
	
	@When("^I copy a meal entry$")
	public void i_copy_a_meal_entry() {
		init();
		userPage.copyMeal();
	}

	@Then("^the new meal entry shows current date-time$")
	public void the_new_meal_entry_shows_current_date_time() {
		init();
		
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/M/dd kk:mm");
		
		LocalDateTime newDateTime = LocalDateTime.parse(userPage.getDateTimeForEntry(0), dtf);
		Assert.assertTrue("Timestamp is not within one minute", (newDateTime.getMinute() - dateTime.getMinute()) < 1);
	}
	
	@Then("^the description and calories are copied$")
	public void the_description_and_calories_are_copied() {
		Assert.assertTrue(false);
	}
	
	@When("^I select a meal entry on table$")
	public void i_select_a_meal_entry_on_table() {
		init();
		userPage.clickAddButton();
		userPage.selectFirstRow();
	}

	@Then("^the \"([^\"]*)\" button is enabled$")
	public void the_button_is_enabled(String arg1) {
	    init();
	    Assert.assertTrue("Save button is not enabled", userPage.isSaveButtonEnabled());
	}
	
	@Then("^the calories goal for the day is clearly displayed$")
	public void the_calories_goal_for_the_day_is_clearly_displayed() {
	   init();
	   Assert.assertTrue("'Goal Calories' was not found", userPage.isGoalDisplayed());
	}
}
