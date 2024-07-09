package abstractomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import priyanshu.framework.cartPage;
import priyanshu.framework.orderPage;

public class abstractComponents {
    WebDriver driver;
    public abstractComponents(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement cartMenu;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement orders;

    public cartPage goToCart(){
        cartMenu.click();
        cartPage cp = new cartPage(driver);
        return cp;
    }

    public orderPage goToOrders(){
        orders.click();
        orderPage op = new orderPage(driver);
        return op;
    }
    
    public void waitUntil(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitUntilDisapear(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    
}
