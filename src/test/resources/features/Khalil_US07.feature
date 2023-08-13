@Khalil @db @ui
Feature: Books module
  As a students, I should be able to borrow book

  Scenario: Student borrow new book
    Given the "student" on the home page
    And the user navigates to "Books" page
    And the user searches for "sad" bookk
    When the user clicks Borrow Bookk
    Then verify that book is shown in "Borrowing Books" pagek
    And  verify logged student has same book in databasek