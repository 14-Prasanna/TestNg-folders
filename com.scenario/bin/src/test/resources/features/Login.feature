Feature: Login Functionality
  Description: Validate Login page using Scenario Outline with multiple credentials

  Background: User is on Login Page
    Given User launch the browser
    And User navigates to the Login page

  @Login_With_ScenarioOutline
  Scenario Outline: Verify Login with multiple credentials
    When User enters username as "<email>" and password as "<password>"
    And User clicks on Login button
    Then User should login successfully

    Examples:
      | email                 | password |
      | prasanna1@gmail.com  | admin123 |
      | karthi22@gmail.com   | test123  |