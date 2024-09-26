package koyenamukherjee.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import koyenamukherjee.Abstractreusable6.abstractComponents;

public class Landingpage extends abstractComponents {
	WebDriver driver;
	

	public Landingpage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	

	//WebElement username=driver.findElement(By.id("userEmail"));
	//pagefactory
	
	@FindBy(id="userEmail")
	WebElement username;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(name="login")
	WebElement submit;
	
	@FindBy(xpath="//div[contains(@class,'flyInOut')]")
	WebElement loginError;
			//div[@class='ng-tns-c4-3 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']
	
	public productcatalog loginIntoApplication(String email,String pwd)
	{
		username.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		productcatalog pg=new productcatalog(driver);
		return pg;
		
	}
	public String getLoginError()
	{
		waitForWebElementToAppear(loginError);
		return loginError.getText();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}

}
