
Feature: Register

  Scenario: Register new account
    Given the register form page
    When creates a new user account as:
      | name     | Pablo                |
      | email    | pablo@mailinator.com |
      | password | picapiedra           |
