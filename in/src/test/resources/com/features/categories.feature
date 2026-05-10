Feature: Prasanna Venkatesh K 04_05_2026 - Categories Feature Validation

  Background: User is on the Home Page
    Given the user is on the DemoBlaze Home Page

  @Categories
  Scenario Outline: Verify <name> category functionality
    When the user clicks on the "<name>" category
    Then only "<name>" related products should be displayed

    Examples:
      | name     |
      | Phones   |
      | Laptops  |
      | Monitors |


  @ClickView
  Scenario Outline: Verify <Product> details correctly displayed
    When the user clicks on the "<name>" category
    And the user clicks on the product "<Product>"
    Then "<Product>" rate should be verified

    Examples:
      | name    | Product      |
      | Laptops | Sony vaio i5 |
      | Laptops | Sony vaio i7 |