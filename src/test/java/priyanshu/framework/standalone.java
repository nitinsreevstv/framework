package priyanshu.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import priyanshu.TestComponents.BaseTest;

public class standalone extends BaseTest {
    @Test(dataProvider = "getData")
    public void submitOrder(String email, String password, String product, String product2) throws IOException {
        // Properties pro = new Properties();
        // FileInputStream fis = new FileInputStream("src//main//java//main//java//resources//Global.properties");
        // pro.load(fis);
        // String email = pro.getProperty("email");
        // String password = pro.getProperty("password");
        // String product = "ADIDAS ORIGINAL";
        // String product2 = "ZARA COAT 3";
        String successMessage = "THANKYOU FOR THE ORDER.";
        String countryName = "India";
        productPage pp = lp.loginMethod(email, password);
        pp.getProduct();
        pp.addToCart(product);
        pp.addToCart(product2);
        cartPage cp = pp.goToCart();
        cp.cartItems();
        Assert.assertTrue(cp.checkForProduct(product));
        Assert.assertTrue(cp.checkForProduct(product2));
        payment Payment = cp.checkOut();
        Payment.choice(countryName);
        confirmationPage conPage = Payment.submitOrder();
        Assert.assertEquals(successMessage, conPage.successMssg());
    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() throws IOException{
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("src//main//java//main//java//resources//Global.properties");
        pro.load(fis);
        String email = pro.getProperty("email");
        String password = pro.getProperty("password");
        productPage pp = lp.loginMethod(email, password);
        orderPage op = pp.goToOrders();
        String product = "ZARA COAT 3";
        Assert.assertTrue(op.orderDisplay(product));
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][] {{"priyanshu12@gmail.com","123@Abc$#","ZARA COAT 3","ADIDAS ORIGINAL"},{"priyanshu1@gmail.com","Password$1#","ADIDAS ORIGINAL","ZARA COAT 3"}};
    }
}
