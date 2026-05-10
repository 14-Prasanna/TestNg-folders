Feature: Register Account Functionality

  Description:
  Validate Register Account page using DataTable

  Background:
    And User navigates to the Register Account page

  @Register_With_DataTable
  Scenario: Verify Register account creation with multiple user details

    When User enters register details and submits the form
      | firstname | lastname  | email               | telephone  | password | confirmpassword |
      | Prasanna  | Venkatesh | prasanna1@gmail.com | 9876543210 | admin123 | admin123        |

    Then User account should be created successfully