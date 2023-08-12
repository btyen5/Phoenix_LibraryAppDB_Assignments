Feature: Books module
  As a students, I should be able to borrow book
  @booksModule @wip @ui @db
  Scenario: Student borrow new book
    Given the "student" on the home page
    And the user navigates to "Books" page
    And the user searches for "Mustapha book" book
    When the user clicks Borrow Book
    Then verify that book is shown in "Borrowing Books" page
    And  verify logged student has same book in database