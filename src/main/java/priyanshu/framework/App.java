package priyanshu.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class App 
{
    public static void main( String[] args )
    {
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();

       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client");

        driver.findElement(By.className("login-wrapper-footer-text")).click();

        driver.findElement(By.id("firstName")).sendKeys("Priyanshu");
        driver.findElement(By.id("lastName")).sendKeys("Srivastava");
        driver.findElement(By.id("userEmail")).sendKeys("priyanshu12@gmail.com");
        driver.findElement(By.id("userMobile")).sendKeys("7373737373");

        Select select = new Select(driver.findElement(By.xpath("//select[@formcontrolname='occupation']")));
        select.selectByVisibleText("Engineer");

        driver.findElement(By.xpath("//input[@value='Male']")).click();

        String password = "123@Abc$#";
        driver.findElement(By.id("userPassword")).sendKeys(password);
        driver.findElement(By.id("confirmPassword")).sendKeys(password);

        driver.findElement(By.xpath("//input[@formcontrolname=\'required\']")).click();

        driver.findElement(By.id("login")).click();


    }
}
