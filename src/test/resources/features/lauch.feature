@tag1
Feature: Testing Launch functionality

  Scenario Outline: Launch with different launch names
    Given I am on the Launch page
    When I enter the Launch name "<launchName>"
    Then the Launch with name "<launchName>" should be created

    Examples:
      | launchName      |
      | SpaceX Launch   |
      | NASA Launch     |
      | Blue Origin     |

  Scenario Outline: Launch with different launch names (data table)
    Given I am on the Launch page
    When I enter the following Launch names:
      | Launch Name   |
      | <launchName1> |
      | <launchName2> |
      | <launchName3> |
    Then the Launches should be created

    Examples:
      | launchName1   | launchName2   | launchName3   |
      | SpaceX Launch | NASA Launch   | Blue Origin   |