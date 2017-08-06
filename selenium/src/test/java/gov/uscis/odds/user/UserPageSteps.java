package gov.uscis.odds.user;


import org.junit.Assert;
import org.springframework.stereotype.Component;

import com.karsun.kic.tan.duke.Steps;

import cucumber.api.java.en.Then;

@Component
public class UserPageSteps extends Steps {
	private UserPage userPage;
	
	private void init() {
		if (userPage == null) {
			userPage = new UserPage(executionContext);
			userPage.get();
		}
	}
	
	@Then("^my calories for today are displayed$")
	public void my_calories_for_today_are_displayed() {
		init();
		String todaysCalories = executionContext.getCurrentScenarioObj().get("todaysCalories").getAsString();
		Assert.assertEquals("Today's calories do not match", todaysCalories, userPage.getTodaysCalories());
	}

}
