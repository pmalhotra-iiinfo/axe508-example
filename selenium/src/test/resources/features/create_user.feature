Feature: Create user
	As a user,
	I want to create my own Calories-Tracker account
	so that I can have a customized calories-tracking experience

@wip
Scenario: Create new user with valid credentials
Given I am on the login page
When I create a new user
Then my calories for today are displayed
