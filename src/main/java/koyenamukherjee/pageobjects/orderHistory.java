package koyenamukherjee.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import koyenamukherjee.Abstractreusable6.abstractComponents;

  
public class orderHistory extends abstractComponents
{
	WebDriver driver;
	
	public orderHistory(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr/td[2]")
	List <WebElement> itemlists;
	


    public Boolean verifyOrderPlaced(String prodName)
    {
    	Boolean match=itemlists.stream().anyMatch(itemneeded->itemneeded.getText().equalsIgnoreCase(prodName));
    	return match;
    }
   
}

