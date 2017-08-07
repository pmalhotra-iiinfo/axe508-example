Feature: Add, delete meals
	As a user,
	I want to add and delete meals
	so that I can track my eating habits

@smoke
Scenario: Add meals
Given I am on the user page
When I add a meal entry
Then I receive a confirmation message
#And the number of calories for the day is updated

@wip
Scenario: Add and delete meals
Given I am on the user page
When I add meal entries
And I delete a meal entry
Then I receive a confirmation message
And the number of calories for the day is updated

@wip
Scenario: Add meal without its type
Given I am on the user page
When I add a meal entry without its type
Then I receive an error message

@wip
Scenario: Add meal but not specifying calories
Given I am on the user page
When I add a meal entry without specifying calories
Then I receive an error message

