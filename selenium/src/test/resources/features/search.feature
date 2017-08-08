Feature: Search
	As a user,
	I want to search my meal entries
	so that I can better understand my eating habits

@wip
Scenario: Default search - no date or time specified, should return last three days
Given I am on the user page
And there is no data from last three days
When I do not specify a date-time period to search
Then I receive a message "No records found"

@testmenow
Scenario: Date-only search
Given I am on the user page
When I specify a date period to search
Then all existing records are returned

@wip
Scenario: Date-time search
Given I am on the user page
When I specify a date-time period to search
Then all existing records are returned

@wip
Scenario: Time search
Given I am on the user page
When I specify a time period to search
Then all existing records are returned

@wip @negative
Scenario: Default search - start date larger than end date
Given I am on the user page
When I specify a date-time period to search
Then I receive a message "From date cannot be larger than to date"

@wip @negative
Scenario: Default search - start date but no end date
Given I am on the user page
When I specify a date-time period to search
Then I receive a message "Both from and to dates are needed"

@wip @negative
Scenario: Default search - end date but no start date
Given I am on the user page
When I specify a date-time period to search
Then I receive a message "Both from and to dates are needed"


