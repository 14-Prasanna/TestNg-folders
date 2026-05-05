Feature: Purchase functionality of DemoBlaze

  Background:
    Given User navigates to "https://www.demoblaze.com"

  @ValidPurchase
  Scenario: Place order successfully
    Given User adds product "Samsung galaxy s6" to cart
    And User clicks on Cart button
    And User clicks on Place Order button
    When User enters purchase details
    And User clicks on Purchase button
    Then User should see purchase confirmation

  @EmptyPurchase
  Scenario: Place order with empty details
    Given User adds product "Nokia lumia 1520" to cart
    And User clicks on Cart button
    And User clicks on Place Order button
    When User clicks on Purchase button
    Then User should see alert for empty purchase details

  @InvalidPurchase
  Scenario: Place order with partial details
    Given User adds product "Sony vaio i5" to cart
    And User clicks on Cart button
    And User clicks on Place Order button
    When User enters only name and card details
    And User clicks on Purchase button
    Then User should see purchase confirmation