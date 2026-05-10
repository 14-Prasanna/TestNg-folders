Feature: Prasanna Venkatesh K - 5/05/2026
  Description : Using Scenario Outline we need to verify the login

  Background: User launch the Browser
    Given User launch the fireFox Browser
    And User is lanuch the DemoBalze Phone Page

  @Mutiple_InvalidLogin
  Scenario: Verify Login with credentials - Data Table with Header and Multiple
    When the user click the Login click
    Then User enters invalid credentials and login will be unsuccessful with error message
      | username            | password | message              |
      | 123@gmail.com       | admin    | Wrong password.      |
      | admin123@gmail.com? | admin    | User does not exist. |