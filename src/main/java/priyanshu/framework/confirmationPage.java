package priyanshu.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractomponents.abstractComponents;

public class confirmationPage extends abstractComponents{
    WebDriver driver;
    public confirmationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".hero-primary")
    WebElement successMsg;

    public String successMssg(){
        return successMsg.getText();
    }
}
