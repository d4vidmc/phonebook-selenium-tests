
Feature: Register

  Scenario: Register new account
    Given the register form page
    When creates a new user account as:
      | user.name     | jose                |
      | user.email    | jose@mailinator.com |
      | user.password | picapiedra          |
    Then validates "user.name" on header menu
