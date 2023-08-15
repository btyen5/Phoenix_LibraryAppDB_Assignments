package com.library.pages;

import com.library.utility.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UsersPage extends BasePage {

public UsersPage(){
    PageFactory.initElements(Driver.getDriver(),this);
}
    @FindBy(id="user_status")
    public WebElement statusDropdown;

    @FindBy(className="dataTables_info")
    public WebElement userCount;

    public String getUserCount(){
        // Extract data
        String allText = userCount.getText();
        System.out.println(allText);

        int start = allText.indexOf("of")+3;
        int end = allText.indexOf("entries")-1;

        // filter and replace
        return allText.substring(start,end).replace(",","");

    }

}
