#Feature: Prasanna Venkatesh K - 5/05/2026
#  Description : Using Scenario Outline we need to verify the login
#
#  Background: User launch the Browser
#    Given User launch the fireFox Browser
#    And User is lanuch the DemoBalze Phone Page
#
#
#  @InvalidLogin
#  Scenario Outline: Verify Login with credentials
#    When the user click the Login click
#    And User enters the as "<username>" and password as "<password>"
#    And User clicks Login Button
#    Then User should able to see an Message as "<message>"
#
#
#    Examples:
#      | username            | password | message                                |
#      | 123@gmail.com       | admin    | Wrong password.                        |
#      | admin123@gmail.com? | admin    | User does not exist.                    |
#      |                     |          | Please fill out Username and Password. |
