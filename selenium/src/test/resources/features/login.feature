Feature: Login
	As a user,
	I want to login to the Calories Tracker
	so that I can have a customized calories-tracking experience

@smoke
Scenario: Login page welcome screen
Given I am on the login page
Then the application purpose is clearly stated

@smoke
Scenario: Login page contains the welcome image
Given I am on the login page
Then the welcome image is displayed

@story5
Scenario: Login with valid credentials
Given I am on the login page
When I use valid username and password
Then my calories for today are displayed
And all existing records are returned

@smoke
Scenario: Login with invalid username and password
Given I am on the login page
When I use invalid username and password
Then I receive an "Invalid username and/or password. Please verify and reenter." message

