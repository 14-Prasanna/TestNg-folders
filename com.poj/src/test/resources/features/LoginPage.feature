Feature: Prasanna Venkatesh K 07-05-2026 Login TO HRM Application
  Description : Demo learning


  @Valid
  Scenario: Login with Valid details

    Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com/"
    When user enters username and password
    Then user should be able to login successfully and should see Dashboard page

    @InvalidPassword
    Scenario: Login with Invalid details

      Given User is on HRMLogin page "https://opensource-demo.orangehrmlive.com/"
      When user enters username and password
      Then user should be able to login successfully and should see error message
