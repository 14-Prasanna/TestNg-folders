Feature: Prasanna Venkatesh K add the items to the cart

  Description:
  As a user, I want to be able to add items to my shopping cart
  so that I can purchase them later.
  @cart
  Scenario: add the items to the cart

    Given user is Home page

    Then user search for the items and adds
      | itemNameSearch | itemNameCart |
      | mac            | iMac         |
      | mac            | MacBook      |
      | mac            | MacBook Air  |
    And