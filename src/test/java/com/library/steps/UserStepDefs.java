package com.library.steps;

import com.library.pages.UsersPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class UserStepDefs {

    String actualUserCount;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        //Make a connection with library
        //DB_Util.createConnection();

        System.out.println("-----CONNECTION WILL BE DONE WITH BEFORE HOOK-----");
    }
    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {
        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUserCount);

    }
    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {
        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);

        String expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);


        Assert.assertEquals(expectedUserCount,actualUserCount);


        //Close connection
        //DB_Util.destroy();

        System.out.println("-----CONNECTION WILL BE CLOSED WITH AFTER HOOK-----");
    }
    //US08
    UsersPage usersPage=new UsersPage();
    @When("the user selected status {string}")
    public void the_user_selected_status(String status) {
        BrowserUtil.selectOptionDropdown(usersPage.statusDropdown,status);
    }
    String actualCount;
    @When("the gets number of users")
    public void the_gets_number_of_users() {
        BrowserUtil.waitFor(3);
        actualCount = usersPage.getUserCount();
        System.out.println(usersPage.getUserCount());

    }
    @Then("verify {string} status users count matching with DB")
    public void verify_status_users_count_matching_with_db(String status) {
        String query="select count(*) from users where status='"+status+"'";

        DB_Util.runQuery(query);

        String expectedCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedCount);

        Assert.assertEquals(expectedCount,actualCount);
    }

}
