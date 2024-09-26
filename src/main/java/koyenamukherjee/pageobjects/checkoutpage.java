package koyenamukherjee.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import koyenamukherjee.Abstractreusable6.abstractComponents;

public class checkoutpage extends abstractComponents {
    
	WebDriver driver;
	public checkoutpage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//*[text()='Name on Card ']/following-sibling::input")
	private WebElement nameElement;
	
	@FindBy(xpath="//*[text()='CVV Code ']/following-sibling::input")
	WebElement cvvElement;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryField;
	
	@FindBy(css=".ta-results button span")
	List <WebElement> countryList;
	
	@FindBy(css=".action__submit")
	WebElement finalSubmit;
	
	By results=By.cssSelector(".ta-results");
	public void enterName(String name)
	{
		 nameElement.sendKeys(name);
	}
	public void enterCVV(String cvv)
	{
	 cvvElement.sendKeys(cvv);
	}
	public void selectCountry(String countName) throws InterruptedException
	{
		 countryField.sendKeys(countName);
		 waitForElementToAppear(results);
		 WebElement cou=countryList.stream().filter(country->country.getText().equalsIgnoreCase("british indian ocean territory")).findAny().orElse(null);
		 cou.click();
				
	}
	
	public finalpage clickOnSubMit()
	{
		 finalSubmit.click();
		 return new finalpage(driver);
		
	}

}
