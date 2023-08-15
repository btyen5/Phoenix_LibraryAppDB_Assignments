@us08
Feature: Users module
  As a librarian, I should be able to to users by status

  Scenario: verify user status with DB
    Given the "librarian" on the home page
    And the user navigates to "Users" page
    When the user selected status "INACTIVE"
    And the gets number of users
    Then  verify "INACTIVE" status users count matching with DB