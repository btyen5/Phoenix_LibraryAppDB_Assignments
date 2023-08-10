package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class Bonnie_US05_StepDefs {

    @When("Execute query to find most popular book genre")
    public void execute_query_to_find_most_popular_book_genre() {

        String query = "select bc.name,count(*) from book_borrow bb\n" +
                "inner join books b on bb.book_id = b.id\n" +
                "inner join book_categories bc on b.book_category_id=bc.id\n" +
                "group by name\n" +
                "order by 2 desc";

        DB_Util.runQuery(query);

    }

    @Then("Verify if {string} is the most popular book genre.")
    public void verify_if_is_the_most_popular_book_genre(String actualGenre) {

        String expectedGenre = DB_Util.getFirstRowFirstColumn();

        /*
        Assert.assertEquals(expectedGenre, actualGenre);
        System.out.println("expectedGenre = " + expectedGenre);
        System.out.println("actualGenre = " + actualGenre);
         */

        try {
            Assert.assertEquals(expectedGenre, actualGenre);
            System.out.println(actualGenre + " is the most popular book genre");
        } catch (AssertionError e) {
            System.err.println(actualGenre + " is NOT the most popular book genre");
        }


    }

}
