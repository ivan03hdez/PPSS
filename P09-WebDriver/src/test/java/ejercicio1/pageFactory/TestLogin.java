package ejercicio1.pageFactory;

import ejercicio1.pageFactory.LoginPage;
import ejercicio1.pageFactory.ManagerPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

class TestLogin {
    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;


    @BeforeEach
    public void setup(){
        driver = new FirefoxDriver();
        poLogin = PageFactory.initElements(driver, LoginPage.class);

    }

    @Test
    public void test_Login_Correct(){
        String loginPageTitle = poLogin.getLoginTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        poLogin.login("mngr195309", "qubeqUz");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);

        assertTrue(poManagerPage.getHomePageDashboardUserName()
                .toLowerCase().contains("manger id : mngr195309"));

        driver.close();
    }

    @Test
    public void test_Login_Incorrect(){
        String loginPageTitle = poLogin.getLoginTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        poLogin.login("mngr195309", "efefwef");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);

        Alert alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        String m = "User or Password is not valid";
        assertEquals(m, mensaje);
        alert.accept();

        driver.close();
    }
}