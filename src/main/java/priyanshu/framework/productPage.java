package priyanshu.framework;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractomponents.abstractComponents;

public class productPage extends abstractComponents{
    WebDriver driver;
    public productPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".col-lg-4")
    List<WebElement> cart;
    By cartListBy = By.cssSelector(".col-lg-4");

    @FindBy(css = "b")
    WebElement cartList;

    @FindBy(css = ".card-body button:last-of-type")
    WebElement cartButton;
    By cartButtonClick = By.cssSelector(".card-body button:last-of-type");

    @FindBy(id = "toast-container")
    WebElement toast;
    By toastBy = By.id("toast-container");

    @FindBy(css = ".ng-animating")
    WebElement animation;

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement addCart;

    public List<WebElement> getProduct() {
        waitUntil(cartListBy);
        return cart;
    }
    public WebElement productByName(String productName){
        WebElement product = getProduct().stream().filter(s->s.findElement(By.cssSelector("b")).getText().equals(productName)).findAny().orElse(null);
        return product;
    }
    public void addToCart(String productName){
        WebElement prod = productByName(productName);
        prod.findElement(cartButtonClick).click();
        waitUntil(toastBy);
        waitUntilDisapear(animation);   
    }

}
