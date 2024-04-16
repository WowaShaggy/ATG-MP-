Feature: Hello World
  Scenario: Print Hello World
    Given I have a greeting
    When I print the greeting
    Then I see "Hello World"