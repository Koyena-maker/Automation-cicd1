package Automation.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;
import koyenamukherjee.pageobjects.cartpage;
import koyenamukherjee.pageobjects.productcatalog;


public class ErrorValidationsTest extends BaseTest
{

@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
public void loginValidations()
{
	lpg.loginIntoApplication("mani45@gmail.com", "Host@1234");
	Assert.assertEquals("Incorrect email or password.", lpg.getLoginError());
	
}

@Test
public void productValidations() throws InterruptedException
{
	productcatalog pg=lpg.loginIntoApplication("koyena@gmail.com", "Host@1234");
	List <WebElement> names= pg.getAllProducts();
	String prodName="IPHONE 13 PRO";
	String prodNameAnother="ADIDAS ORIGINAL";
	pg.addToCart(prodName);
	pg.addToCart(prodNameAnother);
	cartpage cp=pg.goToCartPage();
	Boolean match=cp.verifyProductNames(prodName);
	Assert.assertTrue(match);
	Boolean matchAnother=cp.verifyProductNames(prodNameAnother);
	Assert.assertTrue(matchAnother);
}
}