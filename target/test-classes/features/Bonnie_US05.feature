Feature: As a data consumer, I want to know genre of books are being borrowed the most

  @db
  Scenario: verify the the common book genre that’s being borrowed
    Given Establish the database connection
    When Execute query to find most popular book genre
    Then Verify if "Fantasy" is the most popular book genre.

