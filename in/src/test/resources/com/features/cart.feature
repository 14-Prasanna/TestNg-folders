Feature: Prasanna Venkatesh K Cart functionality of DemoBlaze

  Background:
    Given User navigates to "https://www.demoblaze.com"

  @AddProduct
  Scenario: Add product to cart
    When User selects product "Samsung galaxy s6"
    And User clicks on Add to cart
    Then User should see product added alert

  @ViewCart
  Scenario: View product in cart
    Given User adds product "Nokia lumia 1520" to cart
    When User clicks on Cart button
    Then User should see the product in cart

  @DeleteProduct
  Scenario: Delete product from cart
    Given User adds product "Sony vaio i5" to cart
    And User clicks on Cart button
    When User deletes the product
    Then Cart should be empty