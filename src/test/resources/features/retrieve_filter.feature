Feature: Testing project filter API

  Scenario Outline: Retrieve project filter data
    Given the API is available
    When I request the project filter for project "<projectName>"
    Then the response status code should be 200

    Examples:
      | projectName |
      | project1    |
      | project2    |
      | project3    |