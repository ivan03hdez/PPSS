package ejercicio2.pageFactory;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

public class TestNewClient {
    WebDriver driver;
    LoginPage poLogin;
    ManagerPage poManagerPage;
    NewCustomerPage poNewCustomerPage;
    DeleteCustomerPage poDeleteCustomerPage;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        poLogin = PageFactory.initElements(driver, LoginPage.class);
    }

    @Test
    public void testTestNewClientOk(){
        String loginPageTitle = poLogin.getLoginTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

        poLogin.login("mngr195309", "qubeqUz");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);

        Assert.assertTrue(poManagerPage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr195309"));

        poNewCustomerPage = poManagerPage.newCustomer();

        Assert.assertTrue(poNewCustomerPage.getHomePageDashboardUserName().toLowerCase().contains("add new customer"));

        poNewCustomerPage.datosNewCustomer("Pedro", "m", "1990-01-01", "Calle Rio Segura", "Guardamar", "SPAIN", "424242",
                "666666666", "iufeiufnerifunerfi@gmail.com", "Pedro1234");

        String ValorEsperado = "Customer Registered Successfully!!!";
        String ValorReal = poNewCustomerPage.getSuccess();
        Assert.assertTrue(ValorReal.contains(ValorEsperado));
        String m4 = poNewCustomerPage.getId();
        System.out.println("id");
        System.out.println(m4);

        poNewCustomerPage.continuar();

        Assert.assertTrue(poManagerPage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr195309"));

        poDeleteCustomerPage = poManagerPage.deleteCustomer();

        Assert.assertTrue(poDeleteCustomerPage.getCabecera().toLowerCase().contains("delete customer form"));

        poDeleteCustomerPage.deleteCustomer(m4);

        //Alert alert = driver.switchTo().alert();
        String mensaje = poNewCustomerPage.alerta();
        Assert.assertTrue(mensaje.equals("Do you really want to delete this Customer?"));
        poNewCustomerPage.aceptar();

        WebDriverWait wait = new WebDriverWait(driver, 300 /*timeout in seconds*/);
        if(wait.until(ExpectedConditions.alertIsPresent())==null)
            System.out.println("alert was not present");

        else{
            //Alert alert2 = driver.switchTo().alert();
            String mensaje2 = poNewCustomerPage.alerta2();
            Assert.assertTrue(mensaje2.equals("Customer deleted Successfully"));
            poNewCustomerPage.aceptar2();
            System.out.println("alert was present");
        }


        driver.close();
    }

    @Test
    public void testTestNewClientDuplicate(){
        String loginPageTitle = poLogin.getLoginTitle();
        assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
        poLogin.login("mngr195309", "qubeqUz");
        poManagerPage = PageFactory.initElements(driver, ManagerPage.class);

        Assert.assertTrue(poManagerPage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr195309"));

        poNewCustomerPage = poManagerPage.newCustomer();

        Assert.assertTrue(poNewCustomerPage.getHomePageDashboardUserName().toLowerCase().contains("add new customer"));

        poNewCustomerPage.datosNewCustomer("Pedroo", "m", "1998-01-01", "Calle Rio Segura", "Guardamar", "SPAIN", "424242",
                "666666666", "pepito5555@gmail.com", "Pedro1234");

        Alert alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        Assert.assertTrue(mensaje.equals("Email Address Already Exist !!"));
        alert.accept();

        driver.close();
    }

}