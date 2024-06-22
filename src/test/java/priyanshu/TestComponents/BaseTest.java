package priyanshu.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import priyanshu.framework.loginPage;

public class BaseTest {
    public WebDriver driver;
    public loginPage lp;
    public WebDriver initDriver() throws IOException{
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("src//main//java//main//java//resources//Global.properties");
        pro.load(fis);
        String broswerName = pro.getProperty("browser");

        if(broswerName.equalsIgnoreCase("Chrome")){
             driver = new ChromeDriver();
        }else if(broswerName.equalsIgnoreCase("firefox")){
             driver = new FirefoxDriver();
        }else if(broswerName.equalsIgnoreCase("Edge")){
             driver = new EdgeDriver();
        } 

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod
    public loginPage launchApplication() throws IOException{
        driver = initDriver();
        lp = new loginPage(driver);
        lp.goTo();
        return lp;
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }
}
