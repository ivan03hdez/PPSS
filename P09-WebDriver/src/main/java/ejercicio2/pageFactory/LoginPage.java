package ejercicio2.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
    WebDriver driver;
    @FindBy(name="uid") WebElement userID;
    @FindBy(name="password") WebElement password;
    @FindBy(name="btnLogin") WebElement login;
    @FindBy(className="barone") WebElement loginTitle;


    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.driver.get("http://demo.guru99.com/V4");
    }

    public ManagerPage login(String user, String pass){
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();
        return PageFactory.initElements(driver, ManagerPage.class);
    }

    public String getLoginTitle(){
        return loginTitle.getText();
    }
}
