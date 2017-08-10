Feature: Search
	As a user,
	I want to search my meal entries
	so that I can better understand my eating habits

@smoke
Scenario: Today search
Given I am on the user page
When I specify a date period to search
Then all existing records are returned

@smoke
Scenario: Date-only search
Given I am on the user page
When I specify a date period to search
Then all existing records are returned

@testmenow
Scenario: Search by meal description, no date specified
Given I am on the user page
When I search for a specific meal description with no dates
Then the last three days of records are displayed

@testmenow
Scenario: Search by meal description, date specified
Given I am on the user page
When I search for a specific meal description with dates
Then all existing records are returned

@wip @negative
Scenario: Default search - start date larger than end date
Given I am on the user page
When I specify a date period to search
Then I receive a message "From date cannot be larger than to date"

@wip @negative
Scenario: Default search - start date but no end date
Given I am on the user page
When I specify a date period to search
Then I receive a message "Both from and to dates are needed"

@wip @negative
Scenario: Default search - end date but no start date
Given I am on the user page
When I specify a date period to search
Then I receive a message "Both from and to dates are needed"


