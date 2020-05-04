Feature: Deleting use cases

  Scenario: Delete all use cases with writing them to txt file as JSON

    Given I have logged in Htec Qa sandbox application
    When I delete use cases
    Then All use cases will be deleted