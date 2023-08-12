package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.BorrowedBooksPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Mustafa_US07_StepDefs {
    BorrowedBooksPage borrowedBooksPage = new BorrowedBooksPage();
    BookPage bookPage = new BookPage();

    private String borrowedBookName; // Replace with the actual borrowed book name
    private WebElement borrowBookButton;


    @Given("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        borrowedBookName = bookName;
        bookPage.search.sendKeys(bookName);
    }

    @When("the user clicks Borrow Book")
    public void the_user_clicks_borrow_book() {
        //BrowserUtil.waitFor(5);
        // Scroll to the "Borrow Book" button using JavaScript
        // Scroll to the "Borrow Book" button using JavaScript
        borrowBookButton = Driver.getDriver().findElement(By.xpath("/html/body/main/section[1]/div[1]/div[3]/div[2]/table/tbody/tr[1]/td[1]/a"));
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", borrowBookButton);

        // Click the button using JavaScript
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", borrowBookButton);

    }

    @Then("verify that book is shown in {string} page")
    public void verify_that_book_is_shown_in_page(String pageName) {
        borrowedBooksPage.navigateModule("Borrowing Books");

        boolean bookFound = false;
        for (WebElement book : borrowedBooksPage.allBorrowedBooksName) {
            try {
                if (book.getText().equals(borrowedBookName)) {
                    bookFound = true;
                    break;
                }
            } catch (StaleElementReferenceException e) {
                // Handle the exception (optional), then continue with the loop
            }
        }
        Assert.assertTrue("The borrowed book is not shown in the " + pageName + " page.", bookFound);
    }

    @Then("verify logged student has same book in database")
    public void verify_logged_student_has_same_book_in_database() {
        // SQL query to retrieve specific data
        String sql = "SELECT full_name, b.name, bb.borrowed_date " +
                "FROM users u " +
                "INNER JOIN book_borrow bb ON u.id = bb.user_id " +
                "INNER JOIN books b ON bb.book_id = b.id " +
                "WHERE full_name = 'Test Student 5'  " +
                "ORDER BY 3 DESC";

        // Run the query using the DB_Util class
        DB_Util.createConnection(); // Create a database connection
        DB_Util.runQuery(sql);      // Run the SQL query

        // Verify that the borrowed book name matches the expected name
        List<String> bookNames = DB_Util.getColumnDataAsList("name");
        Assert.assertTrue("The borrowed book name is not found in the database.", bookNames.contains(borrowedBookName));

        // Close the database connection
        DB_Util.destroy();
    }



    }
