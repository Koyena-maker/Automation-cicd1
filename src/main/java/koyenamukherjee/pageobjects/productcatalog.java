package koyenamukherjee.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import koyenamukherjee.Abstractreusable6.abstractComponents;

public class productcatalog extends abstractComponents {
	WebDriver driver;
	
	public productcatalog(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	
	

	//List <WebElement> names=driver.findElements(By.xpath("//*[@class='card-body']"));
	
	@FindBy(xpath="//*[@class='card-body']")
	List <WebElement> prodList;
	
	@FindBy(css=".ng-animating")
	WebElement loader;
	By products=By.xpath("//*[@class='card-body']");
	By addingToCart=By.cssSelector("[class='card-body'] button:last-of-type");
	By wait=By.cssSelector("[class='card-body'] button:last-of-type");

	public List<WebElement> getAllProducts()
	 {
		 waitForElementToAppear(products);
		 return prodList;
	 }
	
	public WebElement getProductByName(String prodName)
	{
		WebElement element=getAllProducts().stream().filter(name->name.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(prodName)).findAny().orElse(null);
		return element;
	}
	public cartpage addToCart(String prodName) throws InterruptedException
	{
		WebElement nameOfProduct=getProductByName(prodName);
		nameOfProduct.findElement(addingToCart).click();
		waitForElementToAppear(wait);
		waitForElementToDisappear(loader);
		cartpage cp=new cartpage(driver);
		return cp;
	}
	
	
	

}
