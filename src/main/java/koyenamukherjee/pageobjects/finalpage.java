package koyenamukherjee.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import koyenamukherjee.Abstractreusable6.abstractComponents;

public class finalpage extends abstractComponents{
	WebDriver driver;

	public finalpage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//tr[@class='ng-star-inserted']/td/label")
	List <WebElement> ids;
	
	@FindBy(css=".hero-primary")
	WebElement getMsg;
	
	public String printIds()
	{
		    System.out.println("Orders ids are listed below-");
		    for(int i=0;i<ids.size();i++)
		    {
		    	System.out.println(ids.get(i).getText());
		    }
		    String message=getMsg.getText();
			return message;
		    
		   
	}

}
