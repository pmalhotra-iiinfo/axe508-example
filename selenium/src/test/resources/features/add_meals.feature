Feature: Add meals
	As a user,
	I want to add meals
	so that I can track my eating habits

@story2
Scenario: Add meals
Given I am on the user page
When I add a meal entry
Then I receive a confirmation message
And the number of calories for the day is updated

@wip
Scenario: Add and delete meals
Given I am on the user page
When I add meal entries
And I delete a meal entry
Then I receive a confirmation message
And the number of calories for the day is updated

@smoke
Scenario: Save button is disabled unless a row is selected
Given I am on the user page
When I select a meal entry on table
Then the "Save" button is enabled

@smoke
Scenario: Rename 'Max Calories' to 'Goal Calories' 
Given I am on the user page
Then the calories goal for the day is clearly displayed

@wip
Scenario: Add meals, date-time for consecutive entries is today
Given I am on the user page
When I add a meal entry
Then consecutive meal entries show current date-time

@wip
Scenario: Copy meals from previous entry
Given I am on the user page
When I copy a meal entry
Then the new meal entry shows current date-time
#And the description and calories are copied

@wip
Scenario: Add meal
Given I am on the user page
When I add a meal entry
Then I receive a confirmation message
And the number of calories for the day is updated

@wip
Scenario: Add and delete meals
Given I am on the user page
When I add meal entries
And I delete a meal entry
Then I receive a confirmation message
And the number of calories for the day is updated

@wip @negative
Scenario: Add meal without its type
Given I am on the user page
When I add a meal entry without its type
Then I receive an error message

@wip @negative
Scenario: Add meal without the serving size
Given I am on the user page
When I add a meal entry without its serving size
Then I receive an error message

@wip @negative
Scenario: Add meal but not specifying calories
Given I am on the user page
When I add a meal entry without specifying calories
Then I receive an error message

