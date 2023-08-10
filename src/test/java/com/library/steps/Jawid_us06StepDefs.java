package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Map;

public class Jawid_us06StepDefs {

        BookPage bookPage = new BookPage();
        LoginPage loginPage = new LoginPage();

    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {

        loginPage.login(user);
        BrowserUtil.waitFor(3);
    }
    @Given("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        bookPage.navigateModule(moduleName);
        BrowserUtil.waitFor(2);
    }

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book(){
        bookPage.addBook.click();

    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {

        bookPage.bookName.sendKeys(bookName);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {

        bookPage.isbn.sendKeys(isbn);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {

        bookPage.year.sendKeys(year);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {

        bookPage.author.sendKeys(author);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategory){

        bookPage.selectBookCategory(bookCategory);

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String string) {
        bookPage.toastMessage.isDisplayed();
    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String bookName) {

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear= bookPage.year.getAttribute("value");

        System.out.println("actualBookName = " + actualBookName);
        System.out.println("actualISBN = " + actualISBN);

        String query = "select name,isbn,year,author,book_category_id from books where name='"+bookName+"';";
        DB_Util.runQuery(query);
        Map<String, String> bookInfo = DB_Util.getRowMap(1);


        String expectedBookName = bookInfo.get("name");
        String expectedAuthorName = bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");

        System.out.println("expectedBookName = " + expectedBookName);
        System.out.println("expectedISBN = " + expectedISBN);


        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthorName,expectedAuthorName);
        Assert.assertEquals(actualISBN,expectedISBN);
        Assert.assertEquals(actualYear,expectedYear);

    }
}
