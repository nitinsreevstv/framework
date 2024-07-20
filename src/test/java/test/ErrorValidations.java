package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import priyanshu.framework.cartPage;
import priyanshu.framework.confirmationPage;
import priyanshu.framework.payment;
import priyanshu.framework.productPage;
import testComponents.BaseTest;

public class ErrorValidations extends BaseTest{
    @Test
    public void loginValidation() throws InterruptedException{
        lp.loginMethod("priyanshu@qwerty.in","IamKing!123");
        Assert.assertEquals("Incorrect email or password.", lp.errorMessage());
    }
    @Test
    public void productErrorValidation() throws IOException {
        Properties pro = new Properties();
        FileInputStream fis = new FileInputStream("src//main//java//main//java//resources//Global.properties");
        pro.load(fis);
        String email = pro.getProperty("email");
        String password = pro.getProperty("password");
        String product1 = "ADIDAS ORIGINAL";
        String product2 = "ZARA COAT 3";
        String successMessage = "THANKYOU FOR THE ORDER.";
        String countryName = "India";
        productPage pp = lp.loginMethod(email, password);
        pp.getProduct();
        pp.addToCart(product1);
        pp.addToCart(product2);
        cartPage cp = pp.goToCart();
        cp.cartItems();
        Assert.assertTrue(cp.checkForProduct(product1));
        Assert.assertTrue(cp.checkForProduct(product2));
        payment Payment = cp.checkOut();
        Payment.choice(countryName);
        confirmationPage conPage = Payment.submitOrder();
        Assert.assertTrue(conPage.successMssg().equalsIgnoreCase(successMessage));
    }
    
}
