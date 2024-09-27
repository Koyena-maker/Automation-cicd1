package Automation.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import koyenamukherjee.Abstractreusable6.abstractComponents;
import koyenamukherjee.pageobjects.Landingpage;
import koyenamukherjee.pageobjects.cartpage;
import koyenamukherjee.pageobjects.checkoutpage;
import koyenamukherjee.pageobjects.finalpage;
import koyenamukherjee.pageobjects.orderHistory;
import koyenamukherjee.pageobjects.productcatalog;

public class submitOrderTest extends BaseTest{
	String prodName="IPHONE 13 PRO";
	String prodNameAnother="ADIDAS ORIGINAL";
	
	
    @Test(dataProvider="receiveData",groups="Purchase,RunThis")
	public  void placeOrder(HashMap<String,String> input) throws InterruptedException 
	{
	
		productcatalog pg=lpg.loginIntoApplication(input.get("email"),input.get("password"));
		List <WebElement> names= pg.getAllProducts();
		pg.addToCart(input.get("prod1"));
		pg.addToCart(input.get("prod2"));
		Thread.sleep(2000);
		cartpage cp=pg.goToCartPage();
		Boolean match=cp.verifyProductNames(input.get("prod1"));
		Assert.assertTrue(match);
		Boolean matchAnother=cp.verifyProductNames(input.get("prod2"));
		Assert.assertTrue(matchAnother);
		checkoutpage cop=cp.goToCheckout();
		String name="Koyena Mukherjee";
		String cvv="123";
		String countryShortForm="Bri";
		String thankyouMsg="Thankyou for the order.";
        cop.enterName(name);
	    cop.enterCVV(cvv);
	    cop.selectByVisibleText("02",driver.findElement(By.xpath("//*[@class=\"input ddl\"][1]")));
	    cop.selectByVisibleText("25",driver.findElement(By.xpath("//*[@class=\"input ddl\"][2]")));
	    cop.selectCountry(countryShortForm);
	    finalpage fp=cop.clickOnSubMit();
	    String message=fp.printIds();
	    Assert.assertTrue(message.equalsIgnoreCase(thankyouMsg));
	    System.out.println(message);
	   }
    @Test(dependsOnMethods="placeOrder")
    public void orderHistory()
    {
    	productcatalog pg=lpg.loginIntoApplication("mani145@gmail.com", "Host@1234");
    	orderHistory oh=pg.goToOrderHistoryPage();
    	Assert.assertTrue(oh.verifyOrderPlaced(prodName));
    	Assert.assertTrue(oh.verifyOrderPlaced(prodNameAnother));
    }
  
    
    
    @DataProvider
    public Object receiveData() throws IOException
    {

    	List <HashMap<String,String>> data= getJsonDataforHashmap(System.getProperty("user.dir")+"\\src\\test\\java\\Automation\\data\\PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    	
    
    }
//	HashMap<String,String> map=new HashMap<String,String>();
//	map.put("email", "mani145@gmail.com");
//	map.put("password","Host@1234");
//	map.put("prod1","IPHONE 13 PRO");
//	map.put("prod2","ADIDAS ORIGINAL");
//	HashMap<String,String> map1=new HashMap<String,String>();
//	map1.put("email", "mish123@yopmail.com");
//	map1.put("password","Host@1234");
//	map1.put("prod1","ADIDAS ORIGINAL");
//	map1.put("prod2","ZARA COAT 3");
//	return new Object[][] {{"mani145@gmail.com","Host@1234","IPHONE 13 PRO","ADIDAS ORIGINAL"} , {"mish123@yopmail.com","Host@1234","ADIDAS ORIGINAL","ZARA COAT 3"}};

}
