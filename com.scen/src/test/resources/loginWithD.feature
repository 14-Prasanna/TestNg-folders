#Feature: Prasanna Venkatesh K - 5/05/2026
#  Description : Using Scenario with Data Table we need to verify the login
#
#  Background: User launch the Browser
#    Given User launch the fireFox Browser
#    And User is launch the  Phone Page
#      | url |https://demoblaze.com/  |
#
#
#  @InvalidLoginD
#  Scenario: Verify Login with credentials
#    When the user click the Login click
#    And User enters username and password
#      | admin | admin1234 |
#    And User clicks Login Button
#    Then User should able to see an Message as "Wrong password."
#
#
##    Examples:
##      | username            | password | message                                |
##      | 123@gmail.com       | admin    | Wrong password.                        |
##      | admin123@gmail.com? | admin    | User does not exist.                    |
##      |                     |          | Please fill out Username and Password. |
