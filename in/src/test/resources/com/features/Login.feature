Feature: Prasanna Venkatesh K 05_04_2026 - LoginFeatures
  Description: Launch DemoBlaze Application and Verify Login Functionality

  Background: User is on the browser
    Given the user launch the firefox browser
    When the user enters the URL as "https://demoblaze.com/" in the browser
    Then the page title should be verified
    When the user clicks on the Login link
    Then the Login card title should be verified

  @Valid
  Scenario: Verify valid login credentials
    When the user enters username as "admin" and password as "admin"
    And the user clicks the login button
    Then the user should be login successfully and "Welcome admin" should be display
    And the Logout should be displayed instead of Login

  @InvalidPassword
  Scenario: Verify invalid Password login Credentials
    When the user enters username as "admin" and password as "admin12"
    And the user clicks the login button
    Then the alert message "Wrong password." should be display

  @InvalidUserName
  Scenario: Verify invalid Username Login Credentials
    When the user enters username as "admin123" and password as "admin"
    And the user clicks the login button
    Then the alert message "Wrong password." should be display