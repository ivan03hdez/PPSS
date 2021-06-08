package ejercicio2.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteCustomerPage {
    WebDriver driver;
    @FindBy(xpath="/html/body/table/tbody/tr/td/table/tbody/tr[1]/td/p")
    WebElement cabecera;
    @FindBy(name="cusid") WebElement idCustomer;
    @FindBy(name="AccSubmit") WebElement submit;

    public DeleteCustomerPage(WebDriver driver){
        this.driver = driver;
    }

    public void deleteCustomer(String idCustomer){
        this.idCustomer.sendKeys(idCustomer);
        submit.click();
    }


    public String getCabecera(){
        return cabecera.getText();
    }
}