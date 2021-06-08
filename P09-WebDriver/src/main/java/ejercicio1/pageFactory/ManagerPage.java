package ejercicio1.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.*;

public class ManagerPage {
    WebDriver driver;
    @FindBy(xpath = "//table//tr[@class='heading3']") WebElement homePageUserName;
    @FindBy(linkText = "New Customer") WebElement newCustomer;
    @FindBy(linkText = "Log out") WebElement logOut;
    public ManagerPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHomePageDashboardUserName() {
        return homePageUserName.getText();
    }
}
