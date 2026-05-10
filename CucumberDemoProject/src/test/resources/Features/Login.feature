Feature: Login with HRM Application
Background:
Given user is on HRM Login page "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    
  @ValidCredentials
  Scenario: Login with valid credentials
    When user enters username and password
    Then user should able to login successfully and see the Dashboard
@InvalidCredentials
Scenario: Login with Invalid Credentials
When user enters username as "Admin" and password as "admin"
Then User can see the Error message as"Invalid Credentials"
