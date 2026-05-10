@login
Feature: Login Functionality on DemoBlaze
  As a registered user of DemoBlaze
  I want to be able to log in to the application
  So that I can access the dashboard and browse products

  Background: Navigate to DemoBlaze login page
    Given I open the browser and navigate to "https://demoblaze.com/"
    And the page title should be "STORE"
    And I click on the "Log in" navigation link

  @smoke @valid_login
  Scenario: Successful login with valid credentials
    When I enter username "admin" and password "admin"
    And I click the Login button
    Then I should be logged in successfully
    And the welcome message should display "Welcome admin"
    And the logout option should be visible

  @negative @empty_credentials
  Scenario: Login attempt with empty username and password
    When I enter username "" and password ""
    And I click the Login button
    Then an alert should appear with message "Please fill out Username and Password."
    And I accept the alert

  @negative
  Scenario: Login attempt with empty username
    When I enter username "" and password "admin"
    And I click the Login button
    Then an alert should appear with message "Please fill out Username and Password."
    And I accept the alert

  @negative @invalid_login
  Scenario: Login attempt with invalid credentials
    When I enter username "invaliduser123" and password "wrongpass456"
    And I click the Login button
    Then an alert should appear with message "User does not exist."
    And I accept the alert

  @outline @parameterized
  Scenario Outline: Login with multiple credentials using Scenario Outline
    When I enter username "<username>" and password "<password>"
    And I click the Login button
    Then I should see the result "<expectedResult>"

    Examples: Valid Credentials
      | username | password | expectedResult          |
      | admin    | admin    | Welcome admin           |

    Examples: Invalid Credentials
      | username        | password     | expectedResult               |
      | wronguser       | wrongpass    | User does not exist.         |
      | admin           | wrongpass    | Wrong password.              |

  @ui @modal
  Scenario: Login modal should open and close correctly
    Then the login modal should be displayed
    And the username field should be present
    And the password field should be present
    When I close the login modal
    Then the login modal should be closed

  @url_validation @valid_login
  Scenario: Page URL should remain on DemoBlaze after successful login
    When I enter username "admin" and password "admin"
    And I click the Login button
    Then the current URL should contain "demoblaze.com"
    And I should be logged in successfully
