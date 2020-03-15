@web @regression
Feature: Login feature

 Background:
    Given I am on Darksky Home Page

  @Assignment_01
  Scenario: Verify Current Temperature should not be greater or less than the Temperature from Daily Timeline
    Given I am on Darksky Home Page
    Then I verify current temp is not greater or less then temps from daily timeline

  @Assignment_02
  Scenario: Verify timeline is displayed in correct format
    Given I am on Darksky Home Page
    Then I verify timeline is displayed with two hours incremented

  @Assignment_03
  Scenario: Verify individual day temp timeline
    When I expand todays timelineFeature: Login feature
    Then I verify lowest and highest temp is displayed correctly

  @Assignment_04
  Scenario: Verify invalid signup error message
    Given I am on the darksky Register page
      When I click on Register button
      Then I verify I am on Register page by asserting Register header