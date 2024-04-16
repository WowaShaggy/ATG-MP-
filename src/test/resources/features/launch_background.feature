@tag2
Feature: Testing Launch functionality with Background

  Background:
    Given I am logged in
    And I am on the Filters page

  Scenario: Viewing Launchers
    When I navigate to the Launchers section
    Then I should see a list of available launchers

  Scenario: Filtering Launchers by Status
    When I navigate to the Filters section
    And I select "Upcoming" status
    Then I should see only upcoming launchers
    But I should not see past launchers