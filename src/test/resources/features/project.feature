Feature: Register

  Scenario: Register new account
    Given the register form page
    When creates a new user account as:
      | name     | Pedro                |
      | email    | pedro@mailinator.com |
      | password | picapiedra           |
    Then dashboard pages should be displayed
    And validates username on text menu
