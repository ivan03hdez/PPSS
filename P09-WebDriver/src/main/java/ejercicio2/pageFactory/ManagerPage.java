package ejercicio2.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerPage {
    WebDriver driver;
    @FindBy(xpath = "//table//tr[@class='heading3']") WebElement homePageUserName;
    @FindBy(linkText = "New Customer") WebElement newCustomer;
    @FindBy(linkText = "Delete Customer") WebElement deleteCustomer;
    @FindBy(linkText = "Log out") WebElement logOut;
    public ManagerPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHomePageDashboardUserName() {
        return homePageUserName.getText();
    }
    public NewCustomerPage newCustomer(){
        newCustomer.click();
        return PageFactory.initElements(driver, NewCustomerPage.class);
    }

    public DeleteCustomerPage deleteCustomer(){
        deleteCustomer.click();
        return PageFactory.initElements(driver, DeleteCustomerPage.class);
    }
}
