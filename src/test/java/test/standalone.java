package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import priyanshu.framework.cartPage;
import priyanshu.framework.confirmationPage;
import priyanshu.framework.orderPage;
import priyanshu.framework.payment;
import priyanshu.framework.productPage;
import testComponents.BaseTest;

public class standalone extends BaseTest {
    @Test(dataProvider = "getData")
    public void submitOrder(HashMap<String, String> input) throws IOException {
        // Properties pro = new Properties();
        // FileInputStream fis = new FileInputStream("src//main//java//main//java//resources//Global.properties");
        // pro.load(fis);
        // String email = pro.getProperty("email");
        // String password = pro.getProperty("password");
        // String product = "ADIDAS ORIGINAL";
        // String product2 = "ZARA COAT 3";
        String successMessage = "THANKYOU FOR THE ORDER.";
        String countryName = "India";
        productPage pp = lp.loginMethod(input.get("email"), input.get("password"));
        pp.getProduct();
        pp.addToCart(input.get("product1"));
        pp.addToCart(input.get("product2"));
        cartPage cp = pp.goToCart();
        cp.cartItems();
        Assert.assertTrue(cp.checkForProduct(input.get("product1")));
        Assert.assertTrue(cp.checkForProduct(input.get("product2")));
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
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("email", "priyanshu12@gmail.com");
        map.put("password", "123@Abc$#");
        map.put("product1", "ZARA COAT 3");
        map.put("product2", "ADIDAS ORIGINAL");

        HashMap<String, String> map2 = new HashMap<String,String>();
        map2.put("email", "priyanshu1@gmail.com");
        map2.put("password", "Password$1#");
        map2.put("product2", "ZARA COAT 3");
        map2.put("product1", "ADIDAS ORIGINAL");
        return new Object[][] {{map},{map2}};
    }
}
