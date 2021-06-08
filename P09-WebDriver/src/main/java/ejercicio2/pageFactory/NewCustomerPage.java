package ejercicio2.pageFactory;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCustomerPage {
    WebDriver driver;

    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p") WebElement homePageUserName;
    @FindBy(name="name") WebElement name;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[1]") WebElement male;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]/input[2]") WebElement female;
    @FindBy(name= "dob") WebElement dateOfBirth;
    @FindBy(name="addr") WebElement address;
    @FindBy(name="city") WebElement city;
    @FindBy(name="state") WebElement state;
    @FindBy(name="pinno") WebElement pin;
    @FindBy(name="telephoneno") WebElement telephone;
    @FindBy(name="emailid") WebElement email;
    @FindBy(name="password") WebElement password;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[14]/td[2]/input[1]") WebElement submit; //también como los anteriores
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p") WebElement success; //también como los anteriores
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[14]/td/a") WebElement continuar;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]") WebElement id;

    Alert alert;
    Alert alert2;

    public NewCustomerPage(WebDriver driver){
        this.driver = driver;
    }

    public void datosNewCustomer(String name, String gender, String dateOfBirth,
                                 String address, String city, String state, String pin, String telephone,
                                 String email, String password){

        this.name.sendKeys(name);
        if(gender == "m"){
            male.click();
        }
        else {
            female.click();
        }
        this.dateOfBirth.sendKeys(dateOfBirth);
        this.address.sendKeys(address);
        this.city.sendKeys(city);
        this.state.sendKeys(state);
        this.pin.sendKeys(pin);
        this.telephone.sendKeys(telephone);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        this.submit.click();
    }
    public String getHomePageDashboardUserName(){
        return homePageUserName.getText();
    }
    public void continuar(){
        continuar.click();
    }
    public String getSuccess(){
        return success.getText();
    }
    public String getId(){
        return id.getText();
    }


    public String alerta(){
        alert = driver.switchTo().alert();
        String mensaje = alert.getText();
        return mensaje;
    }
    public String alerta2(){
        alert2 = driver.switchTo().alert();
        String mensaje = alert2.getText();
        return mensaje;
    }

    public void aceptar(){
        alert.accept();
    }
    public void aceptar2(){
        alert2.accept();
    }
}
