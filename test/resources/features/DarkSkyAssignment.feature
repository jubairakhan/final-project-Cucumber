@web @regression
Feature: Login feature

 Background:
    Given I am on Darksky Home Page
@Assignment_01
Scenario: Verify individual day temp timel
When I expand todays timelineFeature: Login feature
Then I verify lowest and highest temp is displayed correctly

@Assignment_04
Scenario: Verify invalid signup error message

Given I am on the darksky Register page
When I click on Register button
Then I verify I am on Register page by asserting Register header