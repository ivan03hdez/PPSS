package ejercicio1.pageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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

    public void login(String user, String pass){
        userID.sendKeys(user);
        password.sendKeys(pass);
        login.click();
    }

    public String getLoginTitle(){
        return loginTitle.getText();
    }
}
