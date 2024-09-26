package koyenamukherjee.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import koyenamukherjee.Abstractreusable6.abstractComponents;

  
public class cartpage extends abstractComponents
{
	WebDriver driver;
	
	public cartpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[class='cartSection']")
	List <WebElement> lists;
	
	@FindBy(css=".totalRow button")
	WebElement chckOut;

    public Boolean verifyProductNames(String prodName)
    {
    	Boolean match=lists.stream().anyMatch(itemneeded->itemneeded.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(prodName));
    	return match;
    }
    public checkoutpage goToCheckout() throws InterruptedException
    {
    	Thread.sleep(2000);
    	Actions checkout=new Actions(driver);
	    checkout.moveToElement(chckOut).click().build().perform();
	    checkoutpage cop=new checkoutpage(driver);
		return cop;
    }
}

