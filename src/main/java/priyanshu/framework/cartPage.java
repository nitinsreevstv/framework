package priyanshu.framework;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractomponents.abstractComponents;

public class cartPage extends abstractComponents {
    WebDriver driver;
    public cartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartItem;

    @FindBy(xpath = "//button[text()='Checkout']")
    WebElement checkOutButton;

    public List<WebElement> cartItems(){
        return cartItem;
    }

    public boolean checkForProduct(String productName){
        for(int i = 0; i < cartItems().size(); i++){
            if((cartItems().get(i).getText().equals(productName))){
               return true;
            }   
        }
        return false;
    }
    public payment checkOut(){
        checkOutButton.click();
        payment Payment = new payment(driver);
        return Payment;
    }

}
