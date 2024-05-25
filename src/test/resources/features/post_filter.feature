Feature: Testing POST filter API

  Scenario Outline: Post filter data for different projects
    Given the API is available
    When I send a POST request to create a filter for project "<projectName>"
    Then the response status code should be 200
    And the response body should contain an "id"

    Examples:
      | projectName     | createFilterRQ                                                |
      | default_personal| {"description": "description", "name": "name", "type": "launch", "conditions": [{"condition": "string", "filteringField": "string", "value": "string"}], "orders": [{"isAsc": true, "sortingColumn": "string"}]} |
      | 12              | {"description": "description", "name": "name", "type": "launch", "conditions": [{"condition": "string", "filteringField": "string", "value": "string"}], "orders": [{"isAsc": true, "sortingColumn": "string"}]} |
