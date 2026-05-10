@dashboard
Feature: Dashboard Functionality on DemoBlaze
  As a logged-in user of DemoBlaze
  I want to navigate the dashboard and browse products
  So that I can view categories, products, and site features

  Background: User is logged in to DemoBlaze
    Given I open the browser and navigate to "https://demoblaze.com/"
    And the page title should be "STORE"
    And I click on the "Log in" navigation link
    And I enter username "admin" and password "admin"
    And I click the Login button
    And I should be logged in successfully

  @smoke @navigation
  Scenario: All navigation links should be visible on dashboard
    Then the navigation bar should contain "Home"
    And the navigation bar should contain "Contact"
    And the navigation bar should contain "About us"
    And the navigation bar should contain "Cart"
    And the navigation bar should contain "Log out"
    And the navigation bar should contain "Welcome admin"

  @smoke @categories
  Scenario: Product categories should be displayed on dashboard
    Then I should see the "Phones" category in the sidebar
    And I should see the "Laptops" category in the sidebar
    And I should see the "Monitors" category in the sidebar

  @outline @categories @parameterized
  Scenario Outline: User can filter products by category
    When I click on the "<category>" category
    Then the product list should refresh
    And at least one product should be displayed under "<category>"

    Examples: Product Categories
      | category  |
      | Phones    |
      | Laptops   |
      | Monitors  |

  @ui @carousel
  Scenario: Home page carousel should be visible and functional
    When I navigate to the Home page
    Then the carousel should be displayed
    And the next carousel button should be clickable
    When I click the next carousel button
    Then the carousel should advance to the next slide

  @product_details
  Scenario: Clicking a product should open the product detail page
    When I click on the first product in the list
    Then I should be navigated to the product detail page
    And the product name should be visible
    And the product price should be visible
    And the "Add to cart" button should be present

  @outline @product_page
  Scenario Outline: User can view details of specific products
    When I search for product "<productName>" in the catalog
    Then the product "<productName>" should be visible
    And the price should be displayed for "<productName>"

    Examples: Products
      | productName        |
      | Samsung galaxy s6  |
      | Nokia lumia 1520   |
      | Nexus 6            |

  @logout @smoke
  Scenario: User should be able to logout successfully
    When I click on the "Log out" link
    Then the "Log in" navigation link should be visible
    And the "Sign up" navigation link should be visible
    And I should not see "Welcome admin" in the nav bar

  @contact @ui
  Scenario: Contact modal should open from the navigation bar
    When I click on the "Contact" navigation link
    Then the contact modal should be displayed
    And the contact email field should be visible
    And the contact name field should be visible
    And the message field should be visible
    When I close the contact modal
    Then the contact modal should be closed

  @about @ui
  Scenario: About Us modal should open and display video
    When I click on the "About us" navigation link
    Then the about us modal should be displayed
    And the video player should be present
    When I close the about us modal
    Then the about us modal should be closed
